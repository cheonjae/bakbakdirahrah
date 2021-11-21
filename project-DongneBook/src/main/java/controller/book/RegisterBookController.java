package controller.book;

import java.io.File;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.Book;
import model.service.BookManager;
import controller.user.UserSessionUtils;

public class RegisterBookController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(RegisterBookController.class);
	
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

    	String title = null;
    	String author = null;
    	String publisher = null;
    	int price = -1;
    	String description = null;
    	String filename = null;
    	int sold = 0;
    	int cateId = -1;
    	int pageDiscoloration = 0;
    	int coverDamage = 0;
    	int pageDamage = 0;
    	int writing = 0;
    	
    	
    	boolean check = ServletFileUpload.isMultipartContent(request);
    	//전송된 데이터의 인코드 타입이 multipart 인지 여부를 체크한다.
    	//만약 multipart가 아니라면 파일 전송을 처리하지 않는다.
		
		if(check) {//파일 전송이 포함된 상태가 맞다면
			
			// 아래와 같이 하면 Tomcat 내부에 복사된 프로젝트의 폴더 밑에 upload 폴더가 생성됨 
			ServletContext context = request.getServletContext();
			String path = context.getRealPath("/upload");
			File dir = new File(path);
			
			if(!dir.exists()) dir.mkdir();
			//전송된 파일을 저장할 실제 경로를 만든다.
			
			try {
				DiskFileItemFactory factory = new DiskFileItemFactory();
                factory.setSizeThreshold(10 * 1024);
                factory.setRepository(dir);
               
                ServletFileUpload upload = new ServletFileUpload(factory);
                upload.setSizeMax(10 * 1024 * 1024);
                upload.setHeaderEncoding("utf-8");
                                
                List<FileItem> items = (List<FileItem>)upload.parseRequest(request);
                
                for(int i = 0; i < items.size(); ++i) {
                	FileItem item = (FileItem)items.get(i);
                	
                	String value = item.getString("utf-8");
                	
                	if(item.isFormField()) {       		
                		if(item.getFieldName().equals("title")) title = value;
                		else if(item.getFieldName().equals("author")) author = value;
                		else if(item.getFieldName().equals("publisher")) publisher = value;
                		else if(item.getFieldName().equals("price")) price = Integer.parseInt(value);
                		else if(item.getFieldName().equals("cateId")) cateId = Integer.parseInt(value);
                		else if(item.getFieldName().equals("writing")) writing = Integer.parseInt(value);
                		else if(item.getFieldName().equals("pageDiscoloration")) pageDiscoloration = Integer.parseInt(value);
                		else if(item.getFieldName().equals("pageDamage")) pageDamage = Integer.parseInt(value);
                		else if(item.getFieldName().equals("coverDamage")) coverDamage = Integer.parseInt(value);
                		else if(item.getFieldName().equals("description")) description = value;
                	}
                	else {
                		if(item.getFieldName().equals("picture")) {
                			filename = item.getName();
                			if(filename == null || filename.trim().length() == 0) continue;
                			filename = filename.substring(filename.lastIndexOf("\\") + 1);
                			File file = new File(dir, filename);
                			item.write(file);
                		}
                	}
                }
                
			}catch(SizeLimitExceededException e) {
				//업로드 되는 파일의 크기가 지정된 최대 크기를 초과할 때 발생하는 예외처리
				e.printStackTrace();           
            }catch(FileUploadException e) {
            	//파일 업로드와 관련되어 발생할 수 있는 예외 처리
            	e.printStackTrace();
            }catch(Exception e) {            
                e.printStackTrace();
            }
			
			Book book = new Book(
       			0, UserSessionUtils.getLoginUserId(request.getSession()),
       			title, author, publisher, price, description, filename,
       			sold, cateId, pageDiscoloration, coverDamage, pageDamage, writing);
       	
			log.debug("Register Book : {}", book);
       	
			BookManager bookmanager = BookManager.getInstance();
			bookmanager.create(book);
			
			System.out.println("RegisterBookController: "+ book.getTitle());
			
			equest.setAttribute("book", book);
			request.setAttribute("dir", dir);
			request.setAttribute("filename", filename);
		}		
        
		return "/book/detail.jsp";
    }
}
