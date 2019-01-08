package apio.architect.example;


import com.liferay.apio.architect.credentials.Credentials;
import com.liferay.apio.architect.provider.Provider;
import org.osgi.service.component.annotations.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class CredentialsProvider implements Provider<Credentials> {

    @Override
    public Credentials createContext(HttpServletRequest httpServletRequest) {
        return () -> "";
    }

}