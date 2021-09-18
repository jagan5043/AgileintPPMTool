package io.agileintelligence.ppmtool.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.agileintelligence.ppmtool.domain.Project;
import io.agileintelligence.ppmtool.services.MapValidationErrorService;
import io.agileintelligence.ppmtool.services.ProjectService;

@RestController
@RequestMapping("api/project")
public class ProjectController {

	@Autowired
	private ProjectService projectService;

	@Autowired
	MapValidationErrorService mapValidationErrorService;

	@PostMapping("")
	public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result) {
		if (result.hasErrors()) {

			ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationService(result);
			if (errorMap != null) {
				return errorMap;
			}

		}
		
		projectService.saveorUpdateProject(project);
		return new ResponseEntity<Project>(project, HttpStatus.CREATED);

	}
	@GetMapping("/{projectId}")
	public ResponseEntity<?> getProject(@PathVariable String projectId) {
		Project project = projectService.findProject(projectId);
		return new  ResponseEntity<Project>(project,HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public ResponseEntity<?> getAllProjects(){
		List<Project> allProjects = projectService.getAllProjects();
		return new ResponseEntity<List<Project>>(allProjects,HttpStatus.OK);
	}
	
	@DeleteMapping("delete/{projectId}")
	public ResponseEntity<?> deleteProjectById(@PathVariable String projectId) {
		projectService.deleteProjectById(projectId);
		return new ResponseEntity<String>("Project with ID '"+projectId+"' DELETED Successfully!",HttpStatus.OK);
		
	}
	
}
