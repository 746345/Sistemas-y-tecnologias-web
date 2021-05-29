/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package caseta.servlet;

import caseta.bd.FicheroDB;
import caseta.bd.FicheroDBFacade;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;




/**
 *
 * @author fserna
 */
@WebServlet(name = "FileUploader", urlPatterns = {"/FileUploader"})
public class FileUploader extends HttpServlet {
    
    @EJB FicheroDBFacade ficheroFacade;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            
            boolean isMultipart = ServletFileUpload.isMultipartContent(request);
            
            // Create a new file upload handler
System.out.println();
System.out.println();
System.out.println("content-length: " + request.getContentLength());
System.out.println("method: " + request.getMethod());
System.out.println("character encoding: " + request.getCharacterEncoding());  


            if (isMultipart){
                DiskFileItemFactory dfiFactory = new DiskFileItemFactory();
                dfiFactory.setSizeThreshold(500*1024);
                
                ServletFileUpload upload = new ServletFileUpload(dfiFactory);

                // Parse the request
                List<FileItem> items = upload.parseRequest(request);
                Iterator<FileItem> it = items.iterator();
                
                while (it.hasNext()) {
                    FileItem item = it.next();

                    if (item.isFormField()) {
                         System.out.println("4. Form field " + item.getFieldName() + " with value "
                            + item.getString() + " detected.");
                    } else {
                        System.out.println("4. File field " + item.getFieldName() + " with file name "
                            + item.getName() + " detected.");

    System.out.println("5. Name: "+item.getName());                    
    System.out.println("6. MIME: "+item.getContentType());       
                        
    
                        String fileName = item.getName();

                        String path = getServletContext().getRealPath("/");
                        File directorio = new File(path + "/uploads");
                        if (!directorio.exists()) {
                            directorio.mkdirs();
                        }

                        File uploadedFile = new File(directorio + "/" + fileName);
                        System.out.println("Fichero recibido: "+uploadedFile.getAbsolutePath());
                        item.write(uploadedFile);
                        
                        byte[] buffer = new byte[(int)uploadedFile.length()];
                        FileInputStream fis = new FileInputStream(uploadedFile);
                        fis.read(buffer);
                        uploadedFile.delete();
                        directorio.delete();
                        
                        FicheroDB ficheroDB = new FicheroDB();
                        ficheroDB.setFileName(item.getName());
                        ficheroDB.setMimeType(item.getContentType());
                        ficheroDB.setFile(buffer);
                        
                        ficheroFacade.create(ficheroDB);
                        
                    }
                }
            }else{
                // NO es multipart
                response.sendRedirect(response.encodeRedirectURL("error.jsp"));
            }

            
            
            
            
            response.sendRedirect(response.encodeRedirectURL("ok.jsp"));
            
            
        } catch (FileUploadException ex) {
            System.out.println(ex.toString());
            response.sendRedirect(response.encodeRedirectURL("error.jsp"));
        } catch (Exception e){
            System.out.println(e.toString());
            response.sendRedirect(response.encodeRedirectURL("error.jsp"));
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect(response.encodeRedirectURL("error.jsp"));
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>


}
