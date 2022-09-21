package kz.tdcis.poe.democlient.journal;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class JournalPointcuts {

    @Pointcut("execution(public * kz.tdcis.poe.democlient.service.ClientService.createClientData(..))")
    public void createHash(){}
}
