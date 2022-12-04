package app.components;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.entity.Image;
import app.repository.ImageRepository;

@Component
public class ImageComponent {
	@Autowired
	ImageRepository imageRepo;
	
	String UPLOAD_PATH = "images/";
	
	public List<Image> getAllImages() {
		return imageRepo.findAll();
	}
	
	public Image addNewImage(String filename) {
		Image img = imageRepo.findByFileName(filename);
		// Check if file exists.
		if (img != null) {
			throw new RuntimeException(
					String.format("File '%s' already exists.", filename)
					);
		}
		
		img = new Image();
        img.setFileName(filename);
        img = imageRepo.save(img);
        return img;
	}
	
	public Image saveFile(InputStream uploadedInputStream, String filename) {
		Image img = imageRepo.findByFileName(filename);
		// Check if file exists.
		if (img != null) {
			throw new RuntimeException(
					String.format("File '%s' already exists.", filename)
					);
		}
		
		// Create file directory
    	File dir = new File(UPLOAD_PATH);
    	dir.mkdirs();
    	
    	// Instantiate buffer
        int read = 0;
        byte[] bytes = new byte[1024];
        
        // Instantiate file 
        File file = new File(dir, filename);
        
	    // Write image to file
		OutputStream out;
		try {
			out = new FileOutputStream(file);
		} catch (FileNotFoundException e) {
			throw new RuntimeException(String.format("File '%s' not found", filename));
		}
		
		try {
			while ((read = uploadedInputStream.read(bytes)) != -1) {
	        	out.write(bytes, 0, read);
	        }
	        out.flush();
	        out.close();
		} catch (IOException e) {
			throw new RuntimeException("IOException: "+e);
		}
        
        // Save filename to database
        img = new Image();
        img.setFileName(filename);
        img = imageRepo.save(img);
        return img;
	}
	
	public byte[] getImageFile(String filename) {
		Image img = imageRepo.findByFileName(filename);
		// Check if file exists.
		if (img == null) {
			throw new RuntimeException(
					String.format("File '%s' not found.", filename)
					);
		}
		
		// Instantiate file object
    	File dir = new File(UPLOAD_PATH);
        File imagefile = new File(dir, filename);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        
        // Instantiate buffer 
     	byte[] bytes = new byte[1024];
     	int read = -1;
     		
		InputStream is;
		try {
			is = new FileInputStream(imagefile);
		} catch (FileNotFoundException e) {
			throw new RuntimeException(
					String.format("File '%s' not found.", filename)
					);
		}
		
		try {
			while ((read = is.read(bytes)) != -1) {
				baos.write(bytes, 0, read);
	        }
	        baos.flush();
	        is.close();	
		} catch (IOException e) {
			throw new RuntimeException("IOException: " + e);
		}
        
        return baos.toByteArray();
	}
}
