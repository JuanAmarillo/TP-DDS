package controllers.builders;

import java.util.Set;

import domain.Cuenta;
import spark.Request;
import spark.Response;

public class BuilderControllerCuenta extends BuilderController {

	public BuilderControllerCuenta(Request req, Response res) {
		super(req, res);
	}
	
	public BuilderControllerCuenta cuentas(){
		Set<Cuenta> cuentas =  getEmpresa().getCuentasSegun(getPeriodo());
		model.put("cuentas", cuentas);
		return this;
	}

}
