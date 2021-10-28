package com.esprit.microservice;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.HttpEncodingAutoConfiguration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;

import com.google.common.net.MediaType;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;

import reactor.core.publisher.Mono;
@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/annonce")
public class AnnonceRestApi {
	//@Autowired
    //private RestTemplate est;
    @Autowired
    private EurekaClient eurekaClient;
   @Autowired 
   AnnonceService annonceServices;
   
   static RestTemplate restTemplate=new RestTemplate();
   // private WebClient webClient;

   @GetMapping("/list")
   public List<Annonce> getListAnnonce() {
	   
	  // Mono<ClientResponse> responseMono = WebClient.builder().build().get().uri("http://localhost:3004/alluser");
      // System.out.println("wev"+WebClient.builder().build().get().uri("http://localhost:3004/alluser").retrieve());
	   /*Application apps = eurekaClient.getApplication("USERMICROSERVICE");
	    System.out.println("apps:"+apps);
	    InstanceInfo instanceInfo = apps.getInstances().get(0);
	    WebClient.Builder builder=WebClient.builder();
	    System.out.println("//////////////");
        //System.out.println("instance" + instanceInfo+"Port:"+instanceInfo.getPort());
 	    System.out.println("//////////////");
        String url = "http://localhost"  + ":" + instanceInfo.getPort() + "/" + "alluser" ;
        System.out.println("URL" + url+webClient.builder());
        //HttpHeaders headers=new HttpHeaders();
        headers.setAccept(Arrays.asList(org.springframework.http.MediaType.APPLICATION_JSON));
        HttpEntity<String> entity=new HttpEntity<>("parametres",headers);
        ResponseEntity<String> alluser=restTemplate.exchange("http://localhost:8089/hello/", HttpMethod.GET,entity, String.class);
        System.out.println("////////////////////////////");
        System.out.println("oouh" + alluser);
        //System.out.println(restTemplate.getForObject(url,String.class));*/
        return this.annonceServices.annonces_list();
        //return  WebClient.builder().build().get().uri("http://localhost:3004/alluser").retrieve();
	   
   }
	//Delete annonce by id
	@DeleteMapping("/delete/{id}/{user_Id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<String> deleteAnnonce(@PathVariable("id")int id,@PathVariable("user_Id")String user_Id){
	     return new ResponseEntity<>(this.annonceServices.deleteAnnonce(id, user_Id),HttpStatus.OK);	
	}
	//Create new annonce
	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Annonce>createAnnonce(@RequestBody Annonce annonce){
       
		return new ResponseEntity<>(this.annonceServices.addAnnonce(annonce),HttpStatus.CREATED);
	}
	//Update Annonce
	@PutMapping("/update/{id}/{user_Id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<String>updateAnnonce(@PathVariable("id")int id,@PathVariable("user_Id")String user_Id,@RequestBody Annonce annonce){
		return new ResponseEntity<>(this.annonceServices.updateAnnonce(id,user_Id,annonce),HttpStatus.OK);
	}
   //Afficher annonce par id
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Annonce> getSinlgleAnnonce(@PathVariable("id")int id){
		return new ResponseEntity<>(this.annonceServices.getSigleAnnonce(id),HttpStatus.OK);
	}
	//Recherche Avancé par titre
	@GetMapping("/search/{titre}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<Annonce>> searchAnnonce(@PathVariable("titre")String titre){
		return new ResponseEntity<>(this.annonceServices.searchannonce(titre),HttpStatus.OK);
	}
    //Afficher annonce par user
	@GetMapping("/user_Annonce/{user_Id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<Annonce>> annonceByUser(@PathVariable("user_Id")String user_Id){
		return new ResponseEntity<>(this.annonceServices.annonceUser(user_Id),HttpStatus.OK);
	}

}
