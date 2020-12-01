package br.edu.ifpb.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import br.edu.ifpb.domain.enun.StatusTarefa;

/*
 * Autor: Italo Alves <contato.italo1020@gmail.com>
 *
 **/

@Entity
@Table(name = "tarefa")
public class Tarefa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(nullable = false)
	@NotBlank
	private String nome;

	@Column(nullable = false)
	@NotBlank
	private String descricao;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dtCriacao;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dt_finalizacao")
	private Date dtFinalizacao;

	@Enumerated(EnumType.STRING)
	private StatusTarefa status;

	@ManyToOne
	private Usuario usuario;

	public Tarefa() {
		this.status = StatusTarefa.CRIADA;
		this.dtCriacao = new Date();
	}

	public Tarefa(Integer id, String nome, String descricao) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.status = StatusTarefa.CRIADA;
		this.dtCriacao = new Date();
	}

	public Tarefa(String nome, String descricao) {
		this.nome = nome;
		this.descricao = descricao;
		this.status = StatusTarefa.CRIADA;
		this.dtCriacao = new Date();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDtCriacao() {
		return dtCriacao;
	}

	public void setDtCriacao(Date dtCriacao) {
		this.dtCriacao = dtCriacao;
	}

	public Date getDtFinalizacao() {
		return dtFinalizacao;
	}

	public void setDtFinalizacao(Date dtFinalizacao) {
		this.dtFinalizacao = dtFinalizacao;
	}

	public StatusTarefa getStatus() {
		return this.status;
	}

	public void setStatus(StatusTarefa status) {
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Tarefa [id=" + id + ", nome=" + nome + ", descricao=" + descricao + ", dtCriacao=" + dtCriacao
				+ ", dtFinalizacao=" + dtFinalizacao + ", status=" + status + "]";
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Tarefa other = (Tarefa) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
