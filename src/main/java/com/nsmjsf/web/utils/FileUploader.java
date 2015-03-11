package com.nsmjsf.web.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.primefaces.event.FileUploadEvent;

@ManagedBean
@SessionScoped
public class FileUploader {
	private static final Log log = LogFactory.getLog(FileUploader.class);
	private String filePath = null;

	private String targetDirectory = "public/uploads/";
	String absoluteWebPath;
	private String absoluteFilePath;
	private String relativeFilePath;

	public FileUploader() {
		ExternalContext externalContext = FacesContext.getCurrentInstance()
				.getExternalContext();

		absoluteWebPath = externalContext.getRealPath("/");
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getTargetDirectory() {
		return targetDirectory;
	}

	public void setTargetDirectory(String targetDirectory) {
		this.targetDirectory = targetDirectory;
	}

	public String getAbsoluteFilePath() {
		return absoluteFilePath;
	}

	public void setAbsoluteFilePath(String absoluteFilePath) {
		this.absoluteFilePath = absoluteFilePath;
	}

	public String getRelativeFilePath() {
		return relativeFilePath;
	}

	public void setRelativeFilePath(String relativeFilePath) {
		this.relativeFilePath = relativeFilePath;
	}

	public void handleFileUpload(FileUploadEvent event) {
		try {

			
			Long timestamp = (System.currentTimeMillis());
			String uniqFilename = timestamp.toString() + "."
					+ FilenameUtils.getExtension(event.getFile().getFileName());
			File targetFolder = new File(absoluteWebPath + targetDirectory);
			FileUtils.forceMkdir(targetFolder);

			InputStream inputStream = event.getFile().getInputstream();
			OutputStream out = new FileOutputStream(new File(targetFolder,
					uniqFilename));
			int read = 0;
			byte[] bytes = new byte[1024];

			while ((read = inputStream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			inputStream.close();
			out.flush();
			relativeFilePath = targetDirectory + uniqFilename;
			absoluteFilePath = absoluteWebPath + relativeFilePath;
			log.info(absoluteFilePath);
			out.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
