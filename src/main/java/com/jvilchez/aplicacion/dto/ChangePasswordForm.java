package com.jvilchez.aplicacion.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ChangePasswordForm {

	@NotNull
	private Long id;
	
	/*@NotBlank(message="Current Password must not be blank")
	private String currentPassword;*/

	@NotBlank(message="La nueva contraseña no debe estar en blanco")
	private String newPassword;

	@NotBlank(message="Confirmar contraseña no debe estar en blanco")
	private String confirmClave;//no estaba en error.. es independiente

	public ChangePasswordForm() { }
	public ChangePasswordForm(Long id) {this.id = id;}
	public Long getId() { 
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	/*public String getCurrentPassword() {
		return currentPassword;
	}
	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}*/
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getConfirmClave() {
		return confirmClave;//x2
	}
	public void setConfirmClave(String confirmClave) {
		this.confirmClave = confirmClave;//x3
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((confirmClave == null) ? 0 : confirmClave.hashCode());
		/*result = prime * result + ((currentPassword == null) ? 0 : currentPassword.hashCode());*/
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((newPassword == null) ? 0 : newPassword.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChangePasswordForm other = (ChangePasswordForm) obj;
		if (confirmClave == null) {
			if (other.confirmClave != null)
				return false;
		} else if (!confirmClave.equals(other.confirmClave))
			return false;
		/*if (currentPassword == null) {
			if (other.currentPassword != null)
				return false;
		} else if (!currentPassword.equals(other.currentPassword))
			return false;*/
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (newPassword == null) {
			if (other.newPassword != null)
				return false;
		} else if (!newPassword.equals(other.newPassword))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "ChangePasswordForm [id=" + id + ", newPassword=" + newPassword
				+ ", confirmClave=" + confirmClave + "]";
	}
/*, currentPassword=" + currentPassword + "*/	
}
