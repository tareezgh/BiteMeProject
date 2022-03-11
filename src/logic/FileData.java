package logic;

import java.io.Serializable;
import java.sql.Blob;

/**
 * represents a FileData in the system
 */
public class FileData implements Serializable {

	private static final long serialVersionUID = 1L;

	/** PDF File name */
	private String fileName;
	/** File size - type integer */
	private int size;
	/** File type Blob to insert to DB */
	private Blob fileInByte;
	/** Array bytes casting before insert to DB */
	private byte[] byteArray;

	/**
	 * @param fileName   the name of file
	 * @param size       the size of the file
	 * @param fileInByte the file type of Blob
	 */
	public FileData(String fileName, int size, Blob fileInByte) {
		super();
		this.fileName = fileName;
		this.size = size;
		this.fileInByte = fileInByte;
	}
	
	/**
	 * @param fileName   the name of file
	 * @param size       the size of the file
	 * @param byteArray  array type of bytes
	 */
	public FileData(String fileName, int size, byte[] byteArray) {
		super();
		this.fileName = fileName;
		this.size = size;
		this.byteArray = byteArray;
	}

	public byte[] getByteArray() {
		return byteArray;
	}

	public void setByteArray(byte[] byteArray) {
		this.byteArray = byteArray;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Blob getFileInByte() {
		return fileInByte;
	}

	public void setFileInByte(Blob fileInByte) {
		this.fileInByte = fileInByte;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "File [FileID=" + fileName + ", FileSize=" + size + "]";
	}

}
