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
		//�쟾�넚�맂 �뜲�씠�꽣�쓽 �씤肄붾뱶 ���엯�씠 multipart �씤吏� �뿬遺�瑜� 泥댄겕�븳�떎.
		//留뚯빟 multipart媛� �븘�땲�씪硫� �뙆�씪 �쟾�넚�쓣 泥섎━�븯吏� �븡�뒗�떎.
		
		if(check) {//�뙆�씪 �쟾�넚�씠 �룷�븿�맂 �긽�깭媛� 留욌떎硫�
			
			// �븘�옒�� 媛숈씠 �븯硫� Tomcat �궡遺��뿉 蹂듭궗�맂 �봽濡쒖젥�듃�쓽 �뤃�뜑 諛묒뿉 upload �뤃�뜑媛� �깮�꽦�맖 
			ServletContext context = request.getServletContext();
			String path = context.getRealPath("/upload");
			File dir = new File(path);
			
			// Tomcat �쇅遺��쓽 �뤃�뜑�뿉 ���옣�븯�젮硫� �븘�옒�� 媛숈씠 �젅�� 寃쎈줈濡� �뤃�뜑 �씠由꾩쓣 吏��젙�븿
			//File dir = new File("C:/Temp");
			
			if(!dir.exists()) dir.mkdir();
			//�쟾�넚�맂 �뙆�씪�쓣 ���옣�븷 �떎�젣 寃쎈줈瑜� 留뚮뱺�떎.
			
			try {
				DiskFileItemFactory factory = new DiskFileItemFactory();
                //�뙆�씪 �쟾�넚�뿉 ���븳 湲곕낯�쟻�씤 �꽕�젙 Factory �겢�옒�뒪瑜� �깮�꽦�븳�떎.
                factory.setSizeThreshold(10 * 1024);
                //硫붾え由ъ뿉 �븳踰덉뿉 ���옣�븷 �뜲�씠�꽣�쓽 �겕湲곕�� �꽕�젙�븳�떎.
                //10kb �뵫 硫붾え由ъ뿉 �뜲�씠�꽣瑜� �씫�뼱 �뱾�씤�떎.
                factory.setRepository(dir);
                //�쟾�넚�맂 �뜲�씠�꽣�쓽 �궡�슜�쓣 ���옣�븷 �엫�떆 �뤃�뜑瑜� 吏��젙�븳�떎.                
    
                ServletFileUpload upload = new ServletFileUpload(factory);
                //Factory �겢�옒�뒪瑜� �넻�빐 �떎�젣 �뾽濡쒕뱶 �릺�뒗 �궡�슜�쓣 泥섎━�븷 媛앹껜瑜� �꽑�뼵�븳�떎.
                upload.setSizeMax(10 * 1024 * 1024);
                //�뾽濡쒕뱶 �맆 �뙆�씪�쓽 理쒕� �슜�웾�쓣 10MB源뚯� �쟾�넚 �뿀�슜�븳�떎.
                upload.setHeaderEncoding("utf-8");
                //�뾽濡쒕뱶 �릺�뒗 �궡�슜�쓽 �씤肄붾뵫�쓣 �꽕�젙�븳�떎.
                                
                List<FileItem> items = (List<FileItem>)upload.parseRequest(request);
                
                //upload 媛앹껜�뿉 �쟾�넚�릺�뼱 �삩 紐⑤뱺 �뜲�씠�꽣瑜� Collection 媛앹껜�뿉 �떞�뒗�떎.
                for(int i = 0; i < items.size(); ++i) {
                	FileItem item = (FileItem)items.get(i);
                	//commons-fileupload瑜� �궗�슜�븯�뿬 �쟾�넚諛쏆쑝硫� 
                	//紐⑤뱺 parameter�뒗 FileItem �겢�옒�뒪�뿉 �븯�굹�뵫 ���옣�맂�떎.
                	
                	String value = item.getString("utf-8");
                	//�꽆�뼱�삩 媛믪뿉 ���븳 �븳湲� 泥섎━瑜� �븳�떎.
                	
                	if(item.isFormField()) {//�씪諛� �뤌 �뜲�씠�꽣�씪硫�...                		
                		if(item.getFieldName().equals("title")) title = value;
                		//key 媛믪씠 title�씠硫� title 蹂��닔�뿉 媛믪쓣 ���옣�븳�떎.
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
                	else {//�뙆�씪�씠�씪硫�...
                		if(item.getFieldName().equals("picture")) {
                		//key 媛믪씠 picture�씠硫� �뙆�씪 ���옣�쓣 �븳�떎.
                			filename = item.getName();//�뙆�씪 �씠由� �쉷�뱷 (�옄�룞 �븳湲� 泥섎━ �맖)
                			if(filename == null || filename.trim().length() == 0) continue;
                			//�뙆�씪�씠 �쟾�넚�릺�뼱 �삤吏� �븡�븯�떎硫� 嫄대꼫 �쎍�떎.
                			filename = filename.substring(filename.lastIndexOf("\\") + 1);
                			//�뙆�씪 �씠由꾩씠 �뙆�씪�쓽 �쟾泥� 寃쎈줈源뚯� �룷�븿�븯湲� �븣臾몄뿉 �씠由� 遺�遺꾨쭔 異붿텧�빐�빞 �븳�떎.
                			//�떎�젣 C:\Web_Java\aaa.gif�씪怨� �븯硫� aaa.gif留� 異붿텧�븯湲� �쐞�븳 肄붾뱶�씠�떎.
                			File file = new File(dir, filename);
                			item.write(file);
                			//�뙆�씪�쓣 upload 寃쎈줈�뿉 �떎�젣濡� ���옣�븳�떎.
                			//FileItem 媛앹껜瑜� �넻�빐 諛붾줈 異쒕젰 ���옣�븷 �닔 �엳�떎.
                		}
                	}
                }
                
			}catch(SizeLimitExceededException e) {
			//�뾽濡쒕뱶 �릺�뒗 �뙆�씪�쓽 �겕湲곌� 吏��젙�맂 理쒕� �겕湲곕�� 珥덇낵�븷 �븣 諛쒖깮�븯�뒗 �삁�쇅泥섎━
				e.printStackTrace();           
            }catch(FileUploadException e) {
            //�뙆�씪 �뾽濡쒕뱶�� 愿��젴�릺�뼱 諛쒖깮�븷 �닔 �엳�뒗 �삁�쇅 泥섎━
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
			
			request.setAttribute("bookId", book.getBookId());
		}		
        
		return "redirect:/user/main";
    }
}
