package mybean.data;
public class Bean7_1 {
	String[]colname;					//�������
	String[][]tablerecord=null;		//��Ų�ѯ��¼
	public Bean7_1() {
		tablerecord=new String[1][1];
		colname=new String[1];
	}
	public String[] getColname() {
		return colname;
	}
	public void setColname(String[] colname) {
		this.colname = colname;
	}
	public String[][] getTablerecord() {
			return tablerecord;
		}
		public void setTablerecord(String[][] tablerecord) {
			this.tablerecord = tablerecord;
		}
	}
