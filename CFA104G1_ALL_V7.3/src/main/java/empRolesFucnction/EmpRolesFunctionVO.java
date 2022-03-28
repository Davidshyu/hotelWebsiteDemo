package empRolesFucnction;

import java.io.Serializable;
import java.util.List;

import com.emplyee.model.EmplyeeVO;

public class EmpRolesFunctionVO implements Serializable{
	private EmplyeeVO emplyeeVO;
	private Integer roles_id;
	private List<Integer> functionList;
	public EmplyeeVO getEmplyeeVO() {
		return emplyeeVO;
	}
	public void setEmplyeeVO(EmplyeeVO emplyeeVO) {
		this.emplyeeVO = emplyeeVO;
	}
	public Integer getRoles_id() {
		return roles_id;
	}
	public void setRoles_id(Integer roles_id) {
		this.roles_id = roles_id;
	}
	public List<Integer> getFunctionList() {
		return functionList;
	}
	public void setFunctionList(List<Integer> functionList) {
		this.functionList = functionList;
	}
}
