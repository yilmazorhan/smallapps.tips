import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.Input
import java.nio.file.*

class BuildContainerImageTask extends DefaultTask {

    @Input
    String greeting = 'hello from GreetingTask'

    @TaskAction
    def buildImage() {
        makeWorkingDirectory()
    }
    
    def makeWorkingDirectory(){
    	println 'Building working directory'
        File buildDir = this.getProject().getBuildDir()
        File releaseDir = new File(buildDir.absolutePath +'/release/'+ this.getProject().getName());
        releaseDir.mkdirs()
        copyArtifactsToReleaseDirectory(buildDir,releaseDir)
        copyDockerTemplateFile()
    }
    
    def copyArtifactsToReleaseDirectory(buildDir, releaseDir){
        String artifactWithVersion = this.getProject().getName() + "-"+this.getProject().getVersion()+".jar";
        Path oldDir = new File(buildDir.absolutePath +'/libs/'+ artifactWithVersion).toPath();
        Path newDir = new File(releaseDir.absolutePath +"/" + artifactWithVersion).toPath();
        println "Old Dir" + oldDir +", newDir "+ newDir
        Files.copy(oldDir,newDir);
    }


}