package com.cloud9.biz.models.vo;

import com.roroclaw.base.annotation.ApiFieldMeta;
import com.roroclaw.base.bean.MemoryCache;

import java.io.File;

/**
 * 文件上传 返回对象
 * 
 * @author dxz
 * 
 */
public class VFileObj {
	@ApiFieldMeta(name = "文件访问全路径", value = "fullDir", type = ApiFieldMeta.DATA_TYPE_STRING)
	private String fullDir = "";
	@ApiFieldMeta(name = "文件访问相对路径", value = "shortDir", type = ApiFieldMeta.DATA_TYPE_STRING)
	private String shortDir = "";
	@ApiFieldMeta(name = "文件名", value = "fileName", type = ApiFieldMeta.DATA_TYPE_STRING)
	private String fileName;
	@ApiFieldMeta(name = "文件大小", value = "size", type = ApiFieldMeta.DATA_TYPE_INT)
	private Long size;
	@ApiFieldMeta(name = "文件类型文本", value = "type", type = ApiFieldMeta.DATA_TYPE_STRING)
	private String typeText;
	@ApiFieldMeta(name = "文件类型", value = "type", type = ApiFieldMeta.DATA_TYPE_STRING)
	private String type;
	@ApiFieldMeta(name = "原始名称", value = "type", type = ApiFieldMeta.DATA_TYPE_STRING)
	private String originalName;

	/*******************此处为ueditor设置所用的变量名(Star)*****/

	private String state;
	
	private String url;
	
	private String original;
	
	private String source;

	private String name;

	/*******************End*****/

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getOriginalName() {
		return originalName;
	}

	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFullDir() {
		if (fullDir == null || "".equals(fullDir)) {
			fullDir = MemoryCache.getSysConfigKey("fileUpload.accDir")
					+ File.separator + getTypeText() + File.separator
					+ getShortDir();
		}
		return fullDir;
	}

	public String getUrl() {
		return fullDir;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getOriginal() {
		return original;
	}

	public void setOriginal(String original) {
		this.original = original;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public void setFullDir(String fullDir) {
		this.fullDir = fullDir;
	}

	public String getShortDir() {
		return shortDir;
	}

	public void setShortDir(String shortDir) {
		this.shortDir = shortDir;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public String getTypeText() {
		return typeText;
	}

	public void setTypeText(String typeText) {
		this.typeText = typeText;
	}

}
