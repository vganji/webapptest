package com.proquest.interview.phonebook;

public class Person {
	private String name;
	private String phoneNumber;
	private String address;
        
        public Person(){
            
        }
        public Person( String name , String phoneNumber , String address ){
            this.name = name;
            this.phoneNumber = phoneNumber;
            this.address = address;
}
        public String getName(){
            return this.name;
        }
        
        public String getPhoneNumber(){
            return this.phoneNumber;
        }
        
        public String getAddress(){
            return this.address;
        }
        
        public void setName( String name ){
            this.name = name;
        }
        
        public void setPhoneNumber ( String phoneNumber ){
            this.phoneNumber = phoneNumber;
        }
        
        public void setAddress ( String address){
            this.address = address;
        }
        
        public String toString(){
            StringBuffer sb = new StringBuffer();
            sb.append("Name:" + name);
            sb.append("Phone Number:" + phoneNumber);
            sb.append("Address:" + address);
            return sb.toString(); 
        }
}


