package io.agileintelligence.ppmtool.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.agileintelligence.ppmtool.domain.Project;
import io.agileintelligence.ppmtool.exceptions.ProjectIdException;
import io.agileintelligence.ppmtool.repositories.ProjectRepository;

@Service
public class ProjectService {
	
	@Autowired
	private ProjectRepository projectRepository;
	
	public Project saveorUpdateProject(Project project) {
		
		try {
			project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
			return projectRepository.save(project);
		}catch(Exception e) {
			throw new ProjectIdException("Project with ID'"+project.getProjectIdentifier().toUpperCase()+"' already exists");
		}
		
}
	
	public Project findProject(String projectId) {
		
		Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
		if(project == null) {
			throw new ProjectIdException("Project DOES NOT exists");
		}
		return project;
	}
	
	public List<Project> getAllProjects(){
		List<Project> allProjectsList = (List<Project>) projectRepository.findAll();
		if(allProjectsList.size()<=0) {
			throw new ProjectIdException("Project List is empty please add new projects!");
		}
		return allProjectsList;
		
	}
	
	public void deleteProjectById(String projectId) {
		Project project  = projectRepository.findByProjectIdentifier(projectId);
		if(project==null) {
			throw new ProjectIdException("Project DOES NOT exists");
		}
		projectRepository.delete(project);
	}

} 