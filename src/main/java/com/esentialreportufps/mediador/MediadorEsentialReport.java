/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esentialreportufps.mediador;

import com.esentialreportufps.bd.ConnectionBD;
import com.esentialreportufps.controllers.ProcesarArchivoController;
import com.esentialreportufps.dao.DatoDAO;
import com.esentialreportufps.dao.TablaDAO;
import com.esentialreportufps.dao.UsuarioDAO;
import com.esentialreportufps.valueobjects.ColumnaVO;
import com.esentialreportufps.valueobjects.TablaVO;
import com.esentialreportufps.valueobjects.UsuarioVO;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.Key;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.spec.SecretKeySpec;
import javax.json.Json;
import javax.xml.bind.DatatypeConverter;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.formula.functions.Column;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 *
 * @author victor
 */
public class MediadorEsentialReport {

    private UsuarioDAO usuarioDAO;
    private TablaDAO tablaDAO;
    private DatoDAO datoDAO;

    public MediadorEsentialReport() throws SQLException {
        usuarioDAO = new UsuarioDAO();
        tablaDAO = new TablaDAO();
        datoDAO = new DatoDAO();
        ConnectionBD.getConnection();
    }

    public ArrayList<String> validarUsuario(String codigo, String clave) {
        String token = "";
        String tipoUsuario = "";
        ArrayList<String> resultValidacion = new ArrayList<String>();
        UsuarioVO usuarioVO = usuarioDAO.validarUsuario(codigo, clave);
        if (usuarioVO != null) {
            String subject = usuarioVO.toString();
            token = createJWT(usuarioVO.getcodigo(), usuarioVO.getProgramaAcademico(), usuarioVO.toString(), 120000, "report");
            tipoUsuario = String.valueOf(usuarioVO.getTipoUsuario());
        }
        resultValidacion.add(token);
        resultValidacion.add(tipoUsuario);
        return resultValidacion;

    }

    private String createJWT(String codigo, String programaAcademico, String cargaUtil, long tiempoExpiracion, String key) {

        //The JWT signature algorithm we will be using to sign the token
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //We will sign our JWT with our ApiKey secret
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(key);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
//
//        //Let's set the JWT Claims
        JwtBuilder builder = Jwts.builder().setId(codigo)
                .setIssuedAt(now)
                .setSubject(cargaUtil)
                .setIssuer(programaAcademico)
                .signWith(signatureAlgorithm, signingKey);

//        //if it has been specified, let's add the expiration
        if (tiempoExpiracion >= 0) {
            long expMillis = nowMillis + tiempoExpiracion;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }
        //Builds the JWT and serializes it to a compact, URL-safe string
        return builder.compact();
    }

    public boolean validarToken(String token) {
        String[] parts = token.split("\\.");
        Claims claims;
        if (parts.length != 3) {
            System.out.println("Formato del token invalido");
            return false;
        }
        try {
            claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary("report"))
                    .parseClaimsJws(token).getBody();
        } catch (Exception exeption) {
            System.out.println("Token invalido");
            return false;
        }
        return true;
    }

    public ArrayList<UsuarioVO> listarUsuarios() {
        return usuarioDAO.listarUsuarios();
    }

    public boolean registrarUsuario(JsonObject jsonRegistro) throws SQLException {
        UsuarioVO usuarioVO = new UsuarioVO();
        usuarioVO.setcodigo(jsonRegistro.get("codigo").toString());
        usuarioVO.setClave(jsonRegistro.get("clave").toString());
        usuarioVO.setNombre(jsonRegistro.get("nombre").toString());
        usuarioVO.setProgramaAcademico(jsonRegistro.get("programa").toString());
        usuarioVO.setTipoUsuario(Integer.parseInt(jsonRegistro.get("tipoU").toString().replace("\"", "")));
        return usuarioDAO.registrar(usuarioVO);
    }

    public boolean eliminarUsuario(String codigoUsuario) throws SQLException {
        UsuarioVO usuarioVO = new UsuarioVO();
        usuarioVO.setcodigo(codigoUsuario.replaceAll("\"", ""));
        return usuarioDAO.eliminar(usuarioVO);
    }

    public boolean validarTabla(List<ArrayList<ColumnaVO>> columnasTabla, String codigoCreacion, String nombreArchivo) {
        ArrayList<ColumnaVO> nombreColumnas = columnasTabla.get(0);
        ArrayList<ColumnaVO> tipoDColumnas = columnasTabla.get(1);
        TablaVO tablaVO = new TablaVO();
        tablaVO.setNombre(nombreArchivo.replace(" ", "_") + codigoCreacion);
        tablaVO.setcodigoCreacion(codigoCreacion);
        tablaVO.setColumnas(convertirColumnas(nombreColumnas));
        //  validarTipoColumnas(tipoDColumnas);
        //tablaVO.setTiposColumna(tiposColumna);
        return tablaDAO.insertar(tablaVO, nombreColumnas.size());
    }

    public ArrayList<String> convertirColumnas(ArrayList<ColumnaVO> columnas) {
        ArrayList<String> arrayColumnas = new ArrayList<String>();
        for (ColumnaVO columna : columnas) {
            arrayColumnas.add(columna.getNombreColumna().trim());
        }
        return arrayColumnas;
    }

    public ArrayList<String> validarTipoColumnas(ArrayList<ColumnaVO> datosColumnas) {
        ArrayList<String> arrayColumnas = new ArrayList<String>();
        for (ColumnaVO columna : datosColumnas) {
            if (isNumero(columna.getNombreColumna())) {
                System.out.println("numero: " + columna.getNombreColumna());
            } else {
                System.out.println("texto: " + columna.getNombreColumna());
            }
        }
        return arrayColumnas;
    }

    public boolean isNumero(String numero) {
        try {
            Integer.parseInt(numero);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public JsonArray extraerInformacionFile(String ruta) throws IOException {

        InputStream excelStream = null;
        JsonArray jsonArray = new JsonArray();
        try {
            excelStream = new FileInputStream(new File(ruta));
            Workbook workbook = WorkbookFactory.create(excelStream);
            Sheet sheet = workbook.getSheetAt(0);
            Cell cell;
            Row row = null;
            Column column;
            for (int i = 1; i < sheet.getLastRowNum() + 1; i++) {
                row = sheet.getRow(i);
                if (row != null && i != 65535) {
                    JsonObject jsonObject = new JsonObject();
                    for (int j = 0; j < row.getLastCellNum(); j++) {
                        cell = row.getCell(j);
                        if (cell.getCellTypeEnum() == CellType.NUMERIC) {
                            cell.setCellType(CellType.STRING);
                            jsonObject.addProperty(String.valueOf(j), cell.getStringCellValue());
                        } else if (cell.getCellTypeEnum() == CellType.STRING) {
                            jsonObject.addProperty(String.valueOf(j), cell.getStringCellValue());
                        }
                    }
                    jsonArray.add(jsonObject);
                }
            }
        } catch (FileNotFoundException ex) {
        } catch (InvalidFormatException ex) {
            Logger.getLogger(ProcesarArchivoController.class
                    .getName()).log(Level.SEVERE, null, ex);

        } catch (EncryptedDocumentException ex) {
            Logger.getLogger(ProcesarArchivoController.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                excelStream.close();
            } catch (IOException ex) {
            }
        }
        return jsonArray;
    }

    
     public  boolean registrarDatosFile(JsonArray jsonArray, String tabla) throws SQLException {
         
         for (JsonElement jsonObject: jsonArray) {
             JsonObject jsonObjectD = (JsonObject) jsonObject;
             datoDAO.registrar(jsonObjectD, tabla);
         }
        
        return true;
    }
    
    
}
