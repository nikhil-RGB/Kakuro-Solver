package util;
import java.util.*;
public final class UtilityMethods
{

//main method only serves the purpose of testing
public static void main(String[] args)
{
	
}
//Works both in theory and practically-TESTED WITH UNIT TESTER-0 should not be present
public static ArrayList<Long> permute(int sum,int gboxes)
{
	ArrayList<Long> arr=new ArrayList<>(0);
	if(sum==0||gboxes<2)
	{return arr;}
	String min="1";
	String max="9";
	for(int k=0;k<gboxes-1;++k)
	{
		min+=0;
		max+=9;
	}
	long mini=Long.parseLong(min);
	long maxi=Long.parseLong(max);
	for(;mini<=maxi;++mini)
	{
		if(isUnique(mini)&&(summate(mini)==sum))
		{
			arr.add(mini);
		}
	}
return arr;
}
//This method checks if the number inputted in the method has repeat digits,
//and whether the String contains zero
public static boolean isUnique(long num)
{
String numi=num+"";
if(numi.contains("0"))
{
 return false;	
}
for(int k=0;k<numi.length();++k)
{
char ch=numi.charAt(k);
if(numi.indexOf(ch)!=numi.lastIndexOf(ch))
{return false;}
}
return true;
}

//This method sums up a number passed to it as an argument
public static int summate(long num)
{
int S=0;
while(num!=0)
{
S+=num%10;
num=num/10;
}
return S;
}
//CHANGED
//This method filters the results from the permute method subject to a set of constraints
//INFINITE LOOPING OCCURS HERE
public static ArrayList<Long> filter(ArrayList<Long> results,String constraint)
{   
	//if there is no constraint, return an empty array
	if(constraint.length()==0) {return new ArrayList<Long>(0);}
	ArrayList<Long> fresh=new ArrayList<Long>(0);
	for(int k=0;k<results.size();++k)
	{
		boolean flag=true;
		String res=results.get(k).toString();
		CHAR_PARSER:
		for(int k1=0;k1<res.length();++k1)
		{
			char ch1=res.charAt(k1);
			char ch2=constraint.charAt(k1);
			
			if(ch2!='0'&&ch2!=ch1)
			{
				flag=false;
                break CHAR_PARSER;			
			}
		}
		if(flag)
		{
			//Adds res to fresh list
			fresh.add(Long.parseLong(res));
	    }
	}
	return fresh;
}
 
}
