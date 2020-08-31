package com.koreait.pjt.user;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.pjt.MyUtils;
import com.koreait.pjt.ViewResolver;
import com.koreait.pjt.db.UserDAO;
import com.koreait.pjt.vo.UserVO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * Servlet implementation class ProfileSer
 */
@WebServlet("/profile")
public class ProfileSer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	// 프로필 화면 (나의 프로필 이미지, 이미지 변경 가능한 화면)
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserVO loginUser = MyUtils.getLoginUser(request);
		
		request.setAttribute("data", UserDAO.selUser(loginUser.getI_user()));
		ViewResolver.forward("user/profile", request, response);
	}

	// 이미지 변경 처리 -> get방식은 못 씀(주소로 이미지 정보를 넘기는 것은 한계가 있으므로)
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserVO loginUser = MyUtils.getLoginUser(request);
		
		// 절대경로값/img/user/i_user
		String savePath = getServletContext().getRealPath("img") + "/user/" + loginUser.getI_user(); // 어플리캐이션.img파일의 realpath
		System.out.println("savePath : " + savePath);

		// 만약에 폴더(디렉토리)가 없다면 i_user 디렉토리 만들기
		File directory = new File(savePath);
		if(!directory.exists()) {
			directory.mkdirs(); // mkdir은 폴더 하나만 만들 수 있음
		}
		
		int maxFileSize = 10_485_760; // 1024 * 1024 * 10 (10mb) // 최대 파일 사이즈 크기
		String fileNm = "";
		// String originFileNm = "";
		String saveFileNm = "";
		
		try {
			MultipartRequest mr = new MultipartRequest(request, savePath, maxFileSize, 
					"UTF-8", new DefaultFileRenamePolicy());

			System.out.println("mr : " + mr);
			
			Enumeration files = mr.getFileNames();
			
			while(files.hasMoreElements()) {
				String key = (String) files.nextElement();
				fileNm = mr.getFilesystemName(key);
				// originFileNm = mr.getOriginalFileName(key);
				String extension = fileNm.substring(fileNm.lastIndexOf("."));
				saveFileNm = UUID.randomUUID() + extension;
				
				System.out.println("key : " + key);
				System.out.println("fileNm : " + fileNm);
				// System.out.println("originFileNm : " + originFileNm);
				System.out.println("saveFileNm : " + saveFileNm);
				
				
				File oldFile = new File(savePath + "/" + fileNm); // 원하는 파일명으로 만들기
				File newFile = new File(savePath + "/" + saveFileNm); // 자바 메모리상에 파일 객체를 만듦
				// 범용 고유 식별자
				// uuid
				
				oldFile.renameTo(newFile);
			}
						
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}

		if(saveFileNm != null) { // DB에 프로필 파일명 저장
			UserVO param = new UserVO();
			param.setProfile_img(saveFileNm);
			param.setI_user(loginUser.getI_user());			
			UserDAO.updUser(param);
		}
		response.sendRedirect("/profile");
	}

}
