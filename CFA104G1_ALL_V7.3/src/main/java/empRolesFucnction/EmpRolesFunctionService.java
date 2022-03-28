package empRolesFucnction;

import java.util.List;

public class EmpRolesFunctionService {
	
	private EmpRolesFunctionDAO_interface dao ;
	
	public EmpRolesFunctionService() {
		dao = new EmpRolesFunctionDAO();
	}
	
	public List<Integer> getAllFunctionID(Integer empno) {
		return dao.selectFucnctionByEmpno(empno);
	}
}
