import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

public class ShiroTest {

    @Test
    public void shiroTest(){
        // 1.创建SecurityManager对象,通过读取 shiro.ini配置文件创建
        IniSecurityManagerFactory factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        // 2.创建SecurityManager安全管理器对象
        SecurityManager securityManager = factory.createInstance();
        // 3.将安全管理器对象设置到当前环境中
        SecurityUtils.setSecurityManager(securityManager);
        // 4.创建认证（通俗讲，就是登陆）令牌用（一般都使用账号密码令牌）
        UsernamePasswordToken lucy = new UsernamePasswordToken("lucya", "123");
        // 5.创建主体对象，用于登陆
        Subject subject = SecurityUtils.getSubject();
        // 5.1判断是否认证（登陆），没有认证就开始认证
        boolean authenticated = subject.isAuthenticated();
        System.out.println("是否通过登录？" + authenticated);
        if (!authenticated){
            try{
                subject.login(lucy);
            }catch (UnknownAccountException e){
                System.out.println("账号错误");
            }catch (IncorrectCredentialsException e){
                System.out.println("密码错误");
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        //判断是否登录
        System.out.println("是否登录?"+subject.isAuthenticated());
        //退出
        subject.logout();
        System.out.println("是否登录" + subject.isAuthenticated());




    }
}
