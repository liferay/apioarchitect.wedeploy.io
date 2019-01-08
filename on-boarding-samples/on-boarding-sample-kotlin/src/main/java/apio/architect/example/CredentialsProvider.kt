package apio.architect.example

import com.liferay.apio.architect.credentials.Credentials
import com.liferay.apio.architect.provider.Provider
import org.osgi.service.component.annotations.Component

import javax.servlet.http.HttpServletRequest

@Component
class CredentialsProvider : Provider<Credentials> {

    override fun createContext(httpServletRequest: HttpServletRequest) = Credentials { "" }

}