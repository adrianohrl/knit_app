package components.responsible;  

import components.SimpleObject;
import utilities.Utilities;
import utilities.Database;
import utilities.DateTime;
import java.util.Date;

/**
 * 
 * 
 * @author Adriano Henrique Rossette Leite
 * @version 12/21/2013 09:30 pm
 */
public class Responsible extends SimpleObject implements ResponsibleInterface
{
    /**
     * 
     */
    private String username;
    
    /**
     * 
     */
    private String password;
    
    /**
     * 
     */
    private String email;
    
    /**
     * 
     */
    private long accessLevel;
    
    /**
     * 
     */
    private Date passwordUpdate;
    
    /**
     * 
     * @param name
     * @param username
     * @param password
     * @param email
     * @param accessLevel
     * @param passwordUpdate
     * @throws ResponsibleInException 
     */
    public Responsible(String name, String username, String password, String email, long accessLevel, Date passwordUpdate) throws ResponsibleInException
    {
        super(name);
        if (!Responsible.isValidUsername(username))
            throw new ResponsibleInException("Invalid responsible username!!!");
        this.username = username;
        if (!Utilities.isValidPassword(password))
            throw new ResponsibleInException("Invalid responsible password!!!");
        this.password = Utilities.getMD5(password);
        if (!Utilities.isValidEmail(email))
            throw new ResponsibleInException("Invalid responsible e-mail!!!");
        this.email = email;
        if (accessLevel <= 0)
            throw new ResponsibleInException("Invalid responsible access level!!!");
        this.accessLevel = accessLevel;
        if (this.isValidUpdateDate(passwordUpdate))
            throw new ResponsibleInException("Invalid password update date!!!");
        this.passwordUpdate = passwordUpdate;
    }
    
    /**
     * 
     * @param id
     * @throws ResponsibleInException 
     */
    public Responsible(long id) throws ResponsibleInException
    {
        super(id);
        this.username = this.getUsername(id);
        this.password = this.getPassword(id);
        this.email = this.getEmail(id);
        this.accessLevel = this.getAccessLevel(id);
        this.passwordUpdate = this.getPasswordUpdateDate(id);
    }
    
    /**
     * 
     * @param objName
     * @throws ResponsibleInException 
     */
    public Responsible(String objName) throws ResponsibleInException
    {
        super(objName);
        long id = super.getID(objName);
        if (id <= 0)
            throw new ResponsibleInException("This responsible has been not registered yet!!!");
        this.username = this.getUsername(id);
        this.password = this.getPassword(id);
        this.email = this.getEmail(id);
        this.accessLevel = this.getAccessLevel(id);
        this.passwordUpdate = this.getPasswordUpdateDate(id);
    }
    
    /**
     *
     * @return
     */
    @Override
    protected String getClassName()
    {
        return "Responsible";
    }
    
    /**
     *
     * @param name
     * @return
     * @throws ResponsibleInException
     */
    @Override
    public SimpleObject getNew(String name) throws ResponsibleInException
    {
        return new Responsible(name);
    }
    
    /**
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(SimpleObject obj)
    {
        return obj instanceof Responsible && this.equals((Responsible) obj);
    }
    
    /**
     *
     * @param obj
     * @return
     */
    public boolean equals(Responsible obj)
    {
        long id = obj.getID();
        String name = obj.getName();
        return id == super.getID() && name.equalsIgnoreCase(super.getName()) && 
                this.email.equalsIgnoreCase(obj.getEmail()) && 
                this.password.equalsIgnoreCase(obj.getPassword()) && 
                this.username.equalsIgnoreCase(obj.getUsername()) &&
                this.accessLevel == obj.getAccessLevel();
    }
    
    /**
     * 
     * @return 
     */
    @Override
    public String getEmail()
    {
        return this.email;
    }
    
    /**
     * 
     * @return 
     */
    private String getEmail(long id)
    {
        String email = null;
        if (id <= 0)
            throw new ResponsibleInException("Invalid ID!!!");
        String sql = "SELECT Email FROM " + this.getClassName() + "s WHERE Id = " + id;
        email = Database.getStringElement(sql);
        return email;
    }
    
    /**
     * 
     * @param newEmail
     * @throws ResponsibleInException 
     */
    @Override
    public void updateEmail(String newEmail) throws ResponsibleInException, ResponsibleUpException
    {
        if (!Utilities.isValidEmail(newEmail))
            throw new ResponsibleInException("Invalid responsible e-mail!!!");
        String sql = "UPDATE " + this.getClassName() + "s SET Email = \"" + newEmail + "\" WHERE Id = " + super.getID();
        Database.update(sql);
        this.email = newEmail;
    }
    
    /**
     * 
     * @return 
     */
    @Override
    public String getUsername()
    {
        return this.username;
    }
    
    /**
     * 
     * @param id
     * @return 
     */
    private String getUsername(long id)
    {
        String username = null;
        if (id <= 0)
            throw new ResponsibleInException("Invalid ID!!!");
        String sql = "SELECT Username FROM " + this.getClassName() + "s WHERE Id = " + id;
        username = Database.getStringElement(sql);
        return username;
    }
    
    /**
     * 
     * @param username
     * @return 
     */
    public static boolean isValidUsername(String username)
    {
        boolean isValid = false;
        String sql = "SELECT Id FROM Responsibles WHERE Username = \"" + username + "\"";
        long id = Database.getLongElement(sql);
        if (id <= 0)
            isValid = true;
        return isValid;
    }
    
    /**
     * 
     * @param newUsername
     * @throws ResponsibleInException 
     */
    @Override
    public void updateUsername(String newUsername) throws ResponsibleInException
    {
        if (!Responsible.isValidUsername(newUsername))
            throw new ResponsibleInException("Invalid new username!!!");
        String sql = "UPDATE " + this.getClassName() + "s SET Username = \"" + newUsername + "\" WHERE Id = " + super.getID();
        Database.update(sql);
        this.username = newUsername;
    }
    
    /**
     * 
     * @return 
     */
    private String getPassword()
    {
        return this.password;
    }
    
    /**
     * 
     * @param id
     * @return 
     */
    private String getPassword(long id)
    {
        String password = null;
        if (id <= 0)
            throw new ResponsibleInException("Invalid ID!!!");
        String sql = "SELECT Password FROM " + this.getClassName() + "s WHERE Id = " + id;
        password = Database.getStringElement(sql);
        return password;
    }
    
    /**
     * 
     * @param password
     * @return 
     */
    @Override
    public boolean comparePassword(String password)
    {
        return this.password.equals(Utilities.getMD5(password));
    }
    
    /**
     * 
     * @param newPassword
     * @throws ResponsibleInException 
     */
    @Override
    public void updatePassword(String newPassword) throws ResponsibleInException, ResponsibleUpException
    {
        if (!Utilities.isValidPassword(newPassword))
            throw new ResponsibleInException("Invalid new password!!!");
        String sql = "UPDATE " + this.getClassName() + "s SET Password = \"" + newPassword + "\" WHERE Id = " + super.getID();
        Database.update(sql);
        this.password = newPassword;
    }
    
    /**
     * 
     * @return 
     */
    @Override
    public Date getPasswordUpdateDate()
    {
        return this.passwordUpdate;
    }
    
    /**
     * 
     * @param newDate
     * @return
     * @throws ResponsibleInException 
     */
    private boolean isValidUpdateDate(Date newDate) throws ResponsibleInException
    {
        if (newDate == null)
            throw new ResponsibleInException("NewDate must not be null!!!");
        return DateTime.isValidUpdateDate(this.passwordUpdate, newDate);
    }
    
    /**
     * 
     * @param id
     * @return 
     */
    private Date getPasswordUpdateDate(long id)
    {
        Date passwordUpdate = null;
        if (id <= 0)
            throw new ResponsibleInException("Invalid ID!!!");
        String sql = "SELECT PasswordUpdate FROM " + this.getClassName() + "s WHERE Id = " + id;
        passwordUpdate = Database.getDateElement(sql);
        return passwordUpdate;
    }
    
    /**
     * 
     * @param newDate
     * @throws ResponsibleInException
     * @throws ResponsibleUpException 
     */
    @Override
    public void updatePasswordUpdateDate(Date newDate) throws ResponsibleInException, ResponsibleUpException
    {
        if (!this.isValidUpdateDate(newDate))
            throw new ResponsibleInException("Invalid new date!!!");
        String sql = "UPDATE " + this.getClassName() + "s SET PasswordUpdate = \"" + DateTime.formatDateTime(newDate) + "\" WHERE Id = " + super.getID();
        Database.update(sql);
        this.passwordUpdate = newDate;
    }
    
    /**
     * 
     * @return 
     */
    @Override
    public long getAccessLevel()
    {
        return this.accessLevel;
    }
    
    /**
     * 
     * @param id
     * @return 
     */
    private long getAccessLevel(long id)
    {
        long accessLevel = 0;
        if (id <= 0)
            throw new ResponsibleInException("Invalid ID!!!");
        String sql = "SELECT AccessLevel FROM " + this.getClassName() + "s WHERE Id = " + id;
        accessLevel = Database.getLongElement(sql);
        return accessLevel;
    }
    
    /**
     * 
     * @param newLevel
     * @throws ResponsibleInException
     * @throws ResponsibleUpException 
     */
    @Override
    public void updateAccessLevel(long newLevel) throws ResponsibleInException, ResponsibleUpException
    {
        if (newLevel <= 0)
            throw new ResponsibleInException("Invalid new access level!!!");
        String sql = "UPDATE " + this.getClassName() + "s SET AccessLevel = " + newLevel + " WHERE Id = " + super.getID();
        Database.update(sql);
        this.accessLevel = newLevel;
    }
}
 