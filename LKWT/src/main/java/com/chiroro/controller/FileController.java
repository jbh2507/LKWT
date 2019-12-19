package com.chiroro.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.chiroro.domain.FileVO;

import lombok.extern.log4j.Log4j;

@RestController
@Log4j
public class FileController {
	
	private final String TOP_DIR = "C:\\upload";
	
	private String getSaveDir(Date curruntDate) {
		
		SimpleDateFormat stf = new SimpleDateFormat("yyyy\\MM\\dd");
		
		String dateString = stf.format(curruntDate);

		String saveDir = TOP_DIR + "\\" + dateString;

		File dir = new File(saveDir);
		if (!dir.exists())
			dir.mkdirs();
		
		return saveDir;
	}
	
	private byte[] getFileBytes(File file) {
		byte[] result = null;
		
		try {
			result = FileCopyUtils.copyToByteArray(file);
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
		
		return result;
	}
	
	@PostMapping(value = "/upload", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<List<FileVO>> fileUpload(MultipartFile[] uploadfiles) {

		LocalDateTime curLocalDate = LocalDateTime.now();
		
		Date curDate = Date.valueOf(curLocalDate.toLocalDate());

		String saveDir = getSaveDir(curDate);

		List<FileVO> attachList = new ArrayList<>();
		// 저장 파일
		for (MultipartFile uploaded : uploadfiles) {
			String originName = uploaded.getOriginalFilename();
			log.info("File input -------------------------------");
			log.info("\tName: "+originName);
			log.info("\tType"+uploaded.getContentType());
			log.info("\tSize: "+uploaded.getSize());
			
			// 저장할 이름 세팅
			UUID uuid = UUID.randomUUID();
			String uploadFileName = uuid.toString() +"_"+ originName;
			
			
			File saveFile = new File(saveDir, uploadFileName);
			try {
				uploaded.transferTo(saveFile);
				
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}// try-catch
			
			
			// dto 세팅
			FileVO dto = new FileVO();
			dto.setOrginName(originName);
			dto.setFname(uploadFileName);
			dto.setRegDate(curDate);
			
			attachList.add(dto);
		}// for
		
		return new ResponseEntity<>(attachList, HttpStatus.OK);
	}
	
	@GetMapping(value = "/download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	@ResponseBody
	public ResponseEntity<byte[]> fileDownload(String down) {
		log.info("download: "+down);
		
		int idx = down.indexOf('_');
		FileVO vo = new FileVO();
		vo.setFname(down.substring(idx+1));
		vo.setRegDate(new Date(Long.parseLong(down.substring(0, idx))));
		
		byte[] result = null;
		Date regDate = vo.getRegDate();
		String dir = getSaveDir(regDate);
		
		String fileName = vo.getFname();
		
		idx = fileName.indexOf('_');
		String originName = fileName.substring(idx+1);
		
		File file = new File(dir, fileName);
		
		HttpHeaders header = new HttpHeaders();
		try {
			header.add("Content-Disposition",
					"attachment; filename="+new String(originName.getBytes("UTF-8"),"ISO-8859-1"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		result = getFileBytes(file);
		
		
		return new ResponseEntity<>(result, header, HttpStatus.OK);
	}
}
