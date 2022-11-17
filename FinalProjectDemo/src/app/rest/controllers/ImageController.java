package app.rest.controllers;

import java.io.InputStream;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.beans.factory.annotation.Autowired;

import app.components.ImageComponent;
import app.entity.Image;

@Path("/image")
public class ImageController {
	@Autowired
	ImageComponent ic;
	
	@POST
	@Path("/upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public Image upload(
			@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetails) {
		return ic.saveFile(uploadedInputStream, fileDetails.getFileName());
	}
	
	@GET
	@Path("/show")
	@Produces("image/jpeg")
	public byte[] getImage(@QueryParam("fileName") String fileName) {
		return ic.getImageFile(fileName);
	}
	
	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Image> getAllImages() {
		return ic.getAllImages();
	}
		
}
