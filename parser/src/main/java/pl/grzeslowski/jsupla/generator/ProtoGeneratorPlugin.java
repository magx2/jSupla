package pl.grzeslowski.jsupla.generator;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

public class ProtoGeneratorPlugin implements Plugin<Project> {
    @Override
    public void apply(Project project) {
        project.getTasks().register("helloTask", task -> {
            task.doLast(t -> System.out.println("Hello from MyPlugin!"));
        });
    }
}
