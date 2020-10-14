package com.example.poc.validator;

public class PocValidator {

	private static volatile PocValidator instance;

	private PocValidator() {}

	public static PocValidator getInstance() {
		// Double lock for thread safety.
		if (instance == null) {
			synchronized (PocValidator.class) {
				if (instance == null) {
					instance = new PocValidator();
				}
			}
		}

		return instance;
	}
	public boolean IsValidDeptId(long deptId)
	{
		if(deptId > 0L)
		{
			return true;
		}
		return false;
	}
	
	public boolean IsValidDeptName(String deptName)
	{
		if(!deptName.equals(null))
		{
			return true;
		}
		return false;
	}
}
