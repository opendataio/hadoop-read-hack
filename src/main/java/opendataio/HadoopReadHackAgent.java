package opendataio;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.asm.Advice;

import java.lang.instrument.Instrumentation;

import static net.bytebuddy.matcher.ElementMatchers.named;
import static net.bytebuddy.matcher.ElementMatchers.takesArguments;

public class HadoopReadHackAgent {
    public static void premain(String arguments, Instrumentation instrumentation) {
        new AgentBuilder.Default()
            .type(named("org.apache.hadoop.fs.FSDataInputStream"))
            .transform((builder, typeDescription, classLoader, module, protectionDomain) ->
                builder.method(named("read")
                        .and(takesArguments(long.class, byte[].class, int.class, int.class)))
                    .intercept(Advice.to(MyAdvice.class))
            ).installOn(instrumentation);
    }

    public static class MyAdvice {
        @Advice.OnMethodEnter
        static void enter(@Advice.Argument(value = 1, readOnly = false) byte[] buffer) {
            buffer[0] = 0;
            System.out.println("enter buffer: " + buffer[0]);
        }
    }
}