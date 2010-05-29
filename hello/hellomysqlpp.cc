#include<iostream>
#include<iomanip>
#include<mysql++.h>

/* This is a mysql++ demo program */

int main(int,char**,char**) {
	/*
	try {
		Connection con("mysql","database","master","master",true);
//		Query query=con.query();
//		query << "select * from user";
		Result res=con.store("select * from user");
		Result::iterator i;
		int j=0;
		for(i=res.begin();i!=res.end();i++) {
			Row row=*i;
			cout << j << ":" << row[0] << "\n";
			j++;
		}
		cout << "Records Found: " << res.size() << endl;
	} catch(...)
	{
		cerr << "exception\n";
		return(-1);
	}
	*/
	return(0);
}

//unfortunate template stuff

//template const_subscript_container<MysqlRow, mysql_ColData<const_string>, mysql_ColData<const_string> const, unsigned, int>;
template char* std::basic_string<char, std::char_traits<char>, std::allocator<char> >::_S_construct<char const*>(char const*, char const*, std::allocator<char> const&, std::forward_iterator_tag);
