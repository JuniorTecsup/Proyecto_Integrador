package com.jvilchez.aplicacion.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jvilchez.aplicacion.entity.Enfermedad;
import com.jvilchez.aplicacion.repository.EnfermedadRepository;
import com.jvilchez.aplicacion.service.EnfermedadService;

@Controller
@RequestMapping("/enfermedades")
public class EnfermedadController {
	
	private static String UPLOADED_FOLDER = "src/main/resources/static/img/enfermedades/";//para enfermedad
	
	private static String UPLOADED_Medicina = "src/main/resources/static/img/medicamentos/";
	
    private static final Logger logger = LoggerFactory.getLogger(EnfermedadController.class);
	
	@Value("${app.storage.path}")
	private String STORAGEPATH;
		 
	    @Autowired
	    private EnfermedadRepository uc;
	    
	    @Autowired
		EnfermedadService enfermedadService;

	 
	    @RequestMapping(value="lista", method = RequestMethod.GET)// se añada or defecto "/crud"
	    public String listaEnfermedades(ModelMap mp){
	        mp.put("enfermedades", uc.findAll());
	        return "enfermedades/lista";
	    }
	    
	    @RequestMapping(value="listauser", method = RequestMethod.GET)// se añada or defecto "/crud"
	    public String listauser(ModelMap mp){
	        mp.put("enfermedades", uc.findAll());
	        return "user-comun/inicio_user";//img
	    }
	 
	    @RequestMapping(value="/nuevo", method=RequestMethod.GET)
	    public String nuevo(ModelMap mp){
	        mp.put("enfermedad", new Enfermedad());
	        return "enfermedades/nuevo";//vista
	    } 
	    	     
	 
	    /*@RequestMapping(value="/crear", method=RequestMethod.POST)//hace el insert y se mantiene en crear
	    public String crear(@Valid Enfermedad enfermedad,
	            BindingResult bindingResult, ModelMap mp){
	        if(bindingResult.hasErrors()){
	            return "/enfermedades/nuevo";
	        }else{
	            uc.save(enfermedad);
	            mp.put("enfermedad", enfermedad);
	            return "enfermedades/creado";//modificar el tipo de mape0
	        }
	    }*/

	      
	    //Insertar imagenes Completo Controlador
	    
	    //@PostMapping("/insertar")
	    @RequestMapping(value="/insertar", method=RequestMethod.POST)
	    public String crear(@RequestParam(name="enfermedad_img", required=false) MultipartFile enfermedad_img, @RequestParam(name="medi_g", required=false) MultipartFile medi_g, @RequestParam(name="medi_l", required=false) MultipartFile medi_l, @RequestParam(name="medi_n", required=false) MultipartFile medi_n,@RequestParam("nombre") String nombre, @RequestParam("sintomas") String sintomas, @RequestParam("apto") String apto, @RequestParam("medicamento_g") String medicamento_g, @RequestParam("medicamento_l") String medicamento_l, @RequestParam("medicamento_n") String medicamento_n, ModelMap mp) throws Exception {
			logger.info("call crear(" + nombre + ", " + apto + ", " + sintomas + ", " + medicamento_g + ", " + medicamento_l + ", " + medicamento_n + ", " + medi_g + ", " + medi_l + ", " + medi_n + ", " + enfermedad_img + ")");
			
			Enfermedad enfermedad = new Enfermedad();
			enfermedad.setNombre(nombre);
			enfermedad.setSintomas(sintomas);
			enfermedad.setApto(apto);
			enfermedad.setMedicamento_g(medicamento_g);
			enfermedad.setMedicamento_l(medicamento_l);
			enfermedad.setMedicamento_n(medicamento_n);
			
			if (enfermedad_img != null && !enfermedad_img.isEmpty()) {
				//String filename = System.currentTimeMillis() + enfermedad_img.getOriginalFilename().substring(enfermedad_img.getOriginalFilename().lastIndexOf("."));
				String filenombre =enfermedad_img.getOriginalFilename();
				enfermedad.setEnfermedad_img(filenombre);
				
				
		        try {

		            // Get the file and save it somewhere
		            byte[] bytes = enfermedad_img.getBytes();
		            Path path = Paths.get(UPLOADED_FOLDER + enfermedad_img.getOriginalFilename());
		            Files.write(path, bytes);
		            

		        } catch (IOException e) {
		            e.printStackTrace();
		        }
				
				//Files.copy(enfermedad_img.getInputStream(), Paths.get(STORAGEPATH).resolve(filenombre));
			}
			
			if (medi_g != null && !medi_g.isEmpty()) {
				//String filename = System.currentTimeMillis() + medi_g.getOriginalFilename().substring(medi_g.getOriginalFilename().lastIndexOf("."));
				String filenombre =medi_g.getOriginalFilename();
				enfermedad.setMedi_g(filenombre);
				
				try {

		            // Get the file and save it somewhere
		            byte[] bytes = medi_g.getBytes();
		            Path path = Paths.get(UPLOADED_Medicina + medi_g.getOriginalFilename());
		            Files.write(path, bytes);
		            

		        } catch (IOException e) {
		            e.printStackTrace();
		        }
				
				//Files.copy(medi_g.getInputStream(), Paths.get(STORAGEPATH).resolve(filenombre));
			}
			
			if (medi_l != null && !medi_l.isEmpty()) {
				//String filename = System.currentTimeMillis() + medi_l.getOriginalFilename().substring(medi_l.getOriginalFilename().lastIndexOf("."));
				String filenombre =medi_l.getOriginalFilename();
				enfermedad.setMedi_l(filenombre);
				
				try {

		            // Get the file and save it somewhere
		            byte[] bytes = medi_l.getBytes();
		            Path path = Paths.get(UPLOADED_Medicina + medi_l.getOriginalFilename());
		            Files.write(path, bytes);
		            

		        } catch (IOException e) {
		            e.printStackTrace();
		        }
				
				//Files.copy(medi_l.getInputStream(), Paths.get(STORAGEPATH).resolve(filenombre));
			}
			
			if (medi_n != null && !medi_n.isEmpty()) {
				//String filename = System.currentTimeMillis() + medi_n.getOriginalFilename().substring(medi_n.getOriginalFilename().lastIndexOf("."));
				String filenombre =medi_n.getOriginalFilename();
				enfermedad.setMedi_n(filenombre);
				
				try {

		            // Get the file and save it somewhere
		            byte[] bytes = medi_n.getBytes();
		            Path path = Paths.get(UPLOADED_Medicina + medi_n.getOriginalFilename());
		            Files.write(path, bytes);
		            

		        } catch (IOException e) {
		            e.printStackTrace();
		        }
	
				//Files.copy(medi_n.getInputStream(), Paths.get(STORAGEPATH).resolve(filenombre));
			}
			
			//enfermedadService.save(enfermedad);
			uc.save(enfermedad);
            mp.put("enfermedad", enfermedad);
			
			return "enfermedades/creado";
		}
	    
	    
	    
	    @RequestMapping(value="/borrar/{id}", method=RequestMethod.GET)
	    public String borrar(@PathVariable("id") long id, ModelMap mp){
	        uc.deleteById(id);
	        mp.put("enfermedades", uc.findAll());
	        return "enfermedades/lista";
	    }
	    
	    
	    @RequestMapping(value="/editar/{id}", method=RequestMethod.GET)
	    public String editar(@PathVariable("id") long id, Enfermedad enfermedad){
	        //mp.put("enfermedad", uc.findById(id));
	    	Enfermedad enf=enfermedadService.findById(id);
	    	String nombre=enf.getNombre();
	    	String sintomas=enf.getSintomas();
	    	String enfermedad_img=enf.getEnfermedad_img();
	    	String medi_g=enf.getMedi_g();
	    	String medi_l=enf.getMedi_l();
	    	String medi_n=enf.getMedi_n();
	    	String medicamento_g=enf.getMedicamento_g();
	    	String medicamento_l=enf.getMedicamento_l();
	    	String medicamento_n=enf.getMedicamento_n();
	    	String apto=enf.getApto();
	    	
	    	enfermedad.setNombre(nombre);
	    	enfermedad.setSintomas(sintomas);
	    	enfermedad.setEnfermedad_img(enfermedad_img);
	    	enfermedad.setMedi_g(medi_g);
	    	enfermedad.setMedi_l(medi_l);
	    	enfermedad.setMedi_n(medi_n);
	    	enfermedad.setMedicamento_g(medicamento_g);
	    	enfermedad.setMedicamento_l(medicamento_l);
	    	enfermedad.setMedicamento_n(medicamento_n);
	    	enfermedad.setApto(apto);
	        return "enfermedades/editar";
	    }
	    
	    
	    @RequestMapping(value="/vermas/{id}", method=RequestMethod.GET)
	    public String Vermas(@PathVariable("id") long id,Enfermedad enfermedad){
	    	Enfermedad enf=enfermedadService.findById(id);
	    	String medi_g=enf.getMedi_g();
	    	String medi_l=enf.getMedi_l();
	    	String medi_n=enf.getMedi_n();
	    	String medicamento_g=enf.getMedicamento_g();
	    	String medicamento_l=enf.getMedicamento_l();
	    	String medicamento_n=enf.getMedicamento_n();
	    	String apto=enf.getApto();

	    	enfermedad.setMedi_g(medi_g);
	    	enfermedad.setMedi_l(medi_l);
	    	enfermedad.setMedi_n(medi_n);
	    	enfermedad.setMedicamento_g(medicamento_g);
	    	enfermedad.setMedicamento_l(medicamento_l);
	    	enfermedad.setMedicamento_n(medicamento_n);
	    	
	    	//Enfermedad enfermedad=enfermedadService.findById(id);
	    	//String nom=enfermedad.getNombre();
	    	//enfermedad.setNombre(nom);
	        return "enfermedades/vermas";
	    }
	    
	    /*
	    @RequestMapping(value="/creadov2", method = RequestMethod.POST)
	    public String creado(@RequestParam("enfermedad") Enfermedad enfermedad){
	    	//posibilidad
	        return "/enfermedades/creado";
	    } 
	    */
	    
		 
	    @RequestMapping(value="/creado", method = RequestMethod.POST)
	    public String creado(@RequestParam("enfermedad") Enfermedad enfermedad, ModelMap mp){
	    	//No necesita parametro id ya que tenemos toda la lista
	    	Enfermedad enf = uc.findById(enfermedad.getId()).orElse(null);
	        enf.setNombre(enfermedad.getNombre());
	        enf.setMedicamento_g(enfermedad.getMedicamento_g());
	        enf.setMedicamento_l(enfermedad.getMedicamento_l());
	        enf.setMedicamento_n(enfermedad.getMedicamento_n());
	        enf.setSintomas(enfermedad.getSintomas());
	        enf.setApto(enfermedad.getApto());	
	        uc.save(enf);
	        mp.put("enfermedad", enf);
	    	
	        return "/enfermedades/creado";
	    }
	    
	     
	    @RequestMapping(value="/actualizar", method=RequestMethod.POST)
	    public String actualizar(@Valid Enfermedad enfermedad, BindingResult bindingResult, ModelMap mp){
	        
	        Enfermedad enf = uc.findById(enfermedad.getId()).orElse(null);
	        enf.setNombre(enfermedad.getNombre());
	        enf.setMedicamento_g(enfermedad.getMedicamento_g());
	        enf.setMedicamento_l(enfermedad.getMedicamento_l());
	        enf.setMedicamento_n(enfermedad.getMedicamento_n());
	        enf.setSintomas(enfermedad.getSintomas());
	        enf.setApto(enfermedad.getApto());
	        String nada="";
	        //String dato=enfermedad.getEnfermedad_img();
	        if(enfermedad.getEnfermedad_img().equals(nada)) {
	        	System.out.println("LLego aqui y no guardo");        	
	        }else {
	        	enf.setEnfermedad_img(enfermedad.getEnfermedad_img());
	        	//System.out.println("LLego aqui y si guardo: "+dato);   
	        }
	        if(enfermedad.getMedi_g().equals(nada)) {
	        	System.out.println("LLego aqui y no guardo");        	
	        }else {
	        	enf.setMedi_g(enfermedad.getMedi_g());
	        	//System.out.println("LLego aqui y si guardo: "+dato);   
	        }
	        if(enfermedad.getMedi_l().equals(nada)) {
	        	System.out.println("LLego aqui y no guardo");        	
	        }else {
	        	enf.setMedi_l(enfermedad.getMedi_l());
	        	//System.out.println("LLego aqui y si guardo: "+dato);   
	        }
	        if(enfermedad.getMedi_n().equals(nada)) {
	        	System.out.println("LLego aqui y no guardo");        	
	        }else {
	        	enf.setMedi_n(enfermedad.getMedi_n());
	        	//System.out.println("LLego aqui y si guardo: "+dato);   
	        }
	        uc.save(enf);
	        mp.put("enfermedad", enf);
	        return "enfermedades/actualizado";
	    }    
	    
}