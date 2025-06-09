package com.utils;

public class Validators 
{
	public static boolean isBlank(String data)
	{
		if(data == null || data.trim().length()==0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	public static boolean isAlpha(String data)
	{
		String alphaRegEx ="[a-zA-Z\\sa-zA-Z]+";
		return data.matches(alphaRegEx);
	}
	public static boolean isDigit(String data)
	{
		String alphaRegEx ="[0-9]+";
		return data.matches(alphaRegEx);
	}
	public static boolean isPhone(String data)
	{
		String alphaRegEx = "^\\+?[1-9]\\d{1,14}$";
		return data.matches(alphaRegEx);
	}
	public static boolean isEmail(String data)
	{
		String ERegEx = "[a-zA-Z0-9]+@[a-zA-Z]+\\.[a-zA-Z0-9]{2,3}";
		return data.matches(ERegEx);
	}
	public static boolean isPass(String data)
	{
		String PRegEx = "[a-zA-Z]+@[0-9]{4}";
		return data.matches(PRegEx);
	}
	public static boolean isCardValid(String data)
	{
		String PRegEx = "^(?:4[0-9]{12}(?:[0-9]{3})?"          // Visa
                + "|5[1-5][0-9]{14}"                     // MasterCard
                + "|2[2-7][0-9]{14}"                     // MasterCard (new)
                + "|3[47][0-9]{13}"                      // AmEx
                + "|6(?:011|5[0-9]{2})[0-9]{12}"         // Discover
                + "|3(?:0[0-5]|[68][0-9])[0-9]{11}"      // Diners Club
                + "|35[0-9]{14})$";                      // JCB
		return data.matches(PRegEx);
	}
	public static boolean isDate(String data)
	{
		 String Dregex = "^(0[1-9]|1[0-2])/\\d{2}$";
		return data.matches(Dregex);
	}
	public static boolean isCVV(String data)
	{
		String Cregex = "^[0-9]{3,4}$";
		return data.matches(Cregex);
	}
}
