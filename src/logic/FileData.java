package logic;

import java.io.Serializable;
import java.sql.Blob;

/**
 * represents a FileData in the system
 */
public class FileData implements Serializable {

	private static final long serialVersionUID = 1L;

	/** PDF File name */
	private String FileName;
	/** File size - type integer */
	private int size;
	/** File type Blob to insert to DB */
	private Blob FileInByte;
	/** Array bytes casting before insert to DB */
	private byte[] byteArray;

	/**
	 * @param fileName   the name of file
	 * @param size       the size of the file
	 * @param fileInByte the file type of Blob
	 */
	public FileData(String fileName, int size, Blob fileInByte) {
		super();
		this.FileName = fileName;
		this.size = size;
		this.FileInByte = fileInByte;
	}
	
	/**
	 * @param fileName   the name of file
	 * @param size       the size of the file
	 * @param byteArray  array type of bytes
	 */
	public FileData(String fileName, int size, byte[] byteArray) {
		super();
		this.FileName = fileName;
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
		return FileName;
	}

	public void setFileName(String fileName) {
		FileName = fileName;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Blob getFileInByte() {
		return FileInByte;
	}

	public void setFileInByte(Blob fileInByte) {
		FileInByte = fileInByte;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "File [FileID=" + FileName + ", FileSize=" + size + "]";
	}

}
