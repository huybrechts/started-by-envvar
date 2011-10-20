package jenkins.plugins.startedbyenvvar;

import hudson.EnvVars;
import hudson.Extension;
import hudson.model.Cause;
import hudson.model.EnvironmentContributor;
import hudson.model.Run;
import hudson.model.TaskListener;
import org.acegisecurity.Authentication;

import java.io.IOException;

@Extension
public class StartedByEnvVarContributor extends EnvironmentContributor {
    @Override
    public void buildEnvironmentFor(Run r, EnvVars envs, TaskListener listener) throws IOException, InterruptedException {
        Cause.UserCause cause = (Cause.UserCause) r.getCause(Cause.UserCause.class);
        if (cause != null) {
            String name = cause.getUserName();
            envs.put("JENKINS_STARTED_BY", name);
        }
    }
}
