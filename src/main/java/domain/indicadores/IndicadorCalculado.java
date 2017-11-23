package domain.indicadores;

import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import domain.Empresa;

@Entity
@Table(name = "indicadores_precalculados")
public class IndicadorCalculado {

	@Id
	@GeneratedValue
	private Integer id;
	private Double valorExito;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "empresa_id", nullable = false)
	private Empresa empresa;
	private String periodo;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "indicador_id",  nullable=false)
	private Indicador indicador;

	private void setIndicadorCalculado(Indicador indicador,Empresa empresa, String periodo, Double valorExito) {
		this.valorExito = valorExito;
		this.empresa = empresa;
		this.periodo = periodo;
		this.indicador = indicador;
	}

	public IndicadorCalculado() {
	}

	public IndicadorCalculado(Indicador indicador, Empresa empresa, String periodo, Double value) {
		setIndicadorCalculado(indicador, empresa, periodo, value);
	}

	public IndicadorCalculado(Indicador indicador, Empresa empresa, String periodo) {
		setIndicadorCalculado(indicador, empresa, periodo, null);
	}

	public Optional<Double> getValorCalculado() {
		return Optional.ofNullable(valorExito);
	}

	public String getValorString() {
		return getValorExito().orElse(getValorFalla());
	}

	public Optional<String> getValorExito() {
		return getValorCalculado().map(valor -> valor.toString());
	}

	public String getValorFalla() {
		return "No pudo calcularse";
	}

	
	public Indicador getIndicador(){
		return indicador;
	}
	
	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	
	public void setValorExito(Double valorExito) {
		this.valorExito = valorExito;
	}

	public Integer getId() {
		return id;
	}
	
	public String getNombre() {
		return indicador.getNombre();
	}
}
