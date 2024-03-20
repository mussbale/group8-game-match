package com.webservice.MatchCraft.model;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Entity
@Table(name = "user", uniqueConstraints = @UniqueConstraint(columnNames = "userEmail"))
public class User implements UserDetails{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	//@NotEmpty(message = "Username is required!")
	//@Size(min = 3, max = 30, message = "Username must be between 3 and 30 characters")
	//private String userName;

	@NotEmpty(message = "Email is required!")
	@Email(message = "Please enter a valid email!")
	private String userEmail;
	
	@NotEmpty(message = "password is required")
	//@Size(min = 9, max = 25, message = "passwords must be between 9 and 25 characters")
    private String password;
	
	//@NotEmpty(message = "skill levels is required!")
	//private String skillLevel;

	//@NotEmpty(message = "game Preferences is required!")
	//private String gamePreferences;
	
	@Column(updatable = false)
	private Date createdAt;
	
	private Date updatedAt;
	
	//@ElementCollection is used to mark a collection of basic types (strings in this case)
    @ElementCollection
    @CollectionTable(name = "user_attitude", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "attitude")
    private List<String> attitude;

    @ElementCollection
    @CollectionTable(name = "user_communication_style", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "communication_style")
    private List<String> communicationStyle;

    @ElementCollection
    @CollectionTable(name = "user_preferred_game_modes", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "preferred_game_modes")
    private List<String> preferredGameModes;

    @ElementCollection
    @CollectionTable(name = "user_preferred_teammates", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "preferred_teammates")
    private List<String> preferredTeammates;

    @ElementCollection
    @CollectionTable(name = "user_language_preference", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "language_preference")
    private List<String> languagePreference;
    
    @ElementCollection
    @CollectionTable(name = "play_style", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "play_style")
    private List<String> playStyle;

    /**
	 * @return the playStyle
	 */
	public List<String> getPlayStyle() {
		return playStyle;
	}

	/**
	 * @param playStyle the playStyle to set
	 */
	public void setPlayStyle(List<String> playStyle) {
		this.playStyle = playStyle;
	}

	private String timeZone;

    private String playFrequency;
	
	/**
	 * @return the attitude
	 */
	public List<String> getAttitude() {
		return attitude;
	}

	/**
	 * @param attitude the attitude to set
	 */
	public void setAttitude(List<String> attitude) {
		this.attitude = attitude;
	}

	/**
	 * @return the communicationStyle
	 */
	public List<String> getCommunicationStyle() {
		return communicationStyle;
	}

	/**
	 * @param communicationStyle the communicationStyle to set
	 */
	public void setCommunicationStyle(List<String> communicationStyle) {
		this.communicationStyle = communicationStyle;
	}

	/**
	 * @return the preferredGameModes
	 */
	public List<String> getPreferredGameModes() {
		return preferredGameModes;
	}

	/**
	 * @param preferredGameModes the preferredGameModes to set
	 */
	public void setPreferredGameModes(List<String> preferredGameModes) {
		this.preferredGameModes = preferredGameModes;
	}

	/**
	 * @return the preferredTeammates
	 */
	public List<String> getPreferredTeammates() {
		return preferredTeammates;
	}

	/**
	 * @param preferredTeammates the preferredTeammates to set
	 */
	public void setPreferredTeammates(List<String> preferredTeammates) {
		this.preferredTeammates = preferredTeammates;
	}

	/**
	 * @return the languagePreference
	 */
	public List<String> getLanguagePreference() {
		return languagePreference;
	}

	/**
	 * @param languagePreference the languagePreference to set
	 */
	public void setLanguagePreference(List<String> languagePreference) {
		this.languagePreference = languagePreference;
	}

	/**
	 * @return the timeZone
	 */
	public String getTimeZone() {
		return timeZone;
	}

	/**
	 * @param timeZone the timeZone to set
	 */
	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	/**
	 * @return the playFrequency
	 */
	public String getPlayFrequency() {
		return playFrequency;
	}

	/**
	 * @param playFrequency the playFrequency to set
	 */
	public void setPlayFrequency(String playFrequency) {
		this.playFrequency = playFrequency;
	}

	/**
	 * @return the userEmail
	 */
	public String getUserEmail() {
		return userEmail;
	}

	/**
	 * @param userEmail the userEmail to set
	 */
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	private Role role;
	
	/**
	 * @return the role
	 */
	public Role getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(Role role) {
		this.role = role;
	}
//	@OneToOne
//    @JoinColumn(name = "userEmail", referencedColumnName = "email", insertable = false, updatable = false)
//    private Credential credential;
//
//	public User(String userNameString, String email, String skillLevel, String gamePreferences) {
//		this.userNameString = userNameString;
//		this.userEmail = email;
//		this.skillLevel = skillLevel;
//		this.gamePreferences = gamePreferences;
//	}

	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/*public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}*/

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	/**
	 * @return the skillLevel
	 */
	/*public String getSkillLevel() {
		return skillLevel;
	}

	/**
	 * @param skillLevel the skillLevel to set
	 */
	/*public void setSkillLevel(String skillLevel) {
		this.skillLevel = skillLevel;
	}*/

	/**
	 * @return the gamePreferences
	 */
	/*public String getGamePreferences() {
		return gamePreferences;
	}*/

	/**
	 * @param gamePreferences the gamePreferences to set
	 */
	/*public void setGamePreferences(String gamePreferences) {
		this.gamePreferences = gamePreferences;
	}*/
	

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return List.of(new SimpleGrantedAuthority(role.name()));
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return userEmail;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
