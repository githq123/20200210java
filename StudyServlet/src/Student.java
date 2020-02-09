
public class Student {

	String name,pwd;
	Student(){};
	Student(String name,String pwd){
		//this.no=no;
		this.name=name;
		this.pwd=pwd;
		//this.sex=sex;
		//this.age=age;
		//this.dept=dept;
	}
	//public String getNo() {
		//return no;
	//}
	//public void setNo(String no) {
		//this.no = no;
	//}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd=pwd;
	}
	//public String getSex() {
		//return sex;
	//}
	//public void setSex(String sex) {
		//this.sex = sex;
	//}
	//public String getDept() {
		//return dept;
	//}
	//public void setDept(String dept) {
		//this.dept = dept;
	//}
	//public int getAge() {
		//return age;
	//}
	//public void setAge(int age) {
		//this.age = age;
	//}
	public String toString() {
		String result = "name: "+name+", pwd: "+pwd;
		return result;
	}
}
