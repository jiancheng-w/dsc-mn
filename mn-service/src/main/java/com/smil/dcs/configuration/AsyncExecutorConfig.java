package com.smil.dcs.configuration;

import com.smil.dcs.common.UserUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class AsyncExecutorConfig extends AsyncConfigurerSupport {
    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(7);
        executor.setMaxPoolSize(42);
        executor.setQueueCapacity(11);
        executor.setThreadNamePrefix("DCSCustomerAsyncExecutor-");
        executor.initialize();
        return new ContextAwareExecutorDecorator(executor);
    }

    public class ContextAwareExecutorDecorator implements Executor, TaskExecutor {
        private final Executor executor;

        public ContextAwareExecutorDecorator(Executor executor) {
            this.executor = executor;
        }

        @Override
        public void execute(Runnable command) {
            // decorate the task
            Runnable ctxAwareCommand = wrapContextAware(command);
            // execute the decorated task
            executor.execute(ctxAwareCommand);
        }


        private Runnable wrapContextAware(Runnable command) {
            //ThreadLocal<UserUtils.User> threadLocal = UserUtils.getThreadLocal();
            UserUtils.User user = UserUtils.getUserInfo();

            return () -> {
                UserUtils.setUserInfo(user);
                command.run();
            };
        }
    }
}
