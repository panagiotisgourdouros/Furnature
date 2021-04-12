General info:
1) Backend was built with Java 8, you might wanna select appropriate JDK under groupproject properties-> compile-> Java Platform: JDK 1.8
  
2) Folder backendFurnature opens as groupproject name in your favorite IDE

3) We have 3 roles admins, customers, superuser
	-> superuser can perform crud operations on admins
		You can log in with these credentials:
		username:peoplecert & password:Bootcamp12

------------------------------------------------------------------------------------------------------------------------------------------
Database Setup:
  General Steps: MySQL server should run under localhost:3306, otherwise new connection from backend should be made to the database
	         Run SQL Script... option and select FurnatureDB.sql, schema name should be: groupproject
	
  Detailed Steps:  Workbench IDE database import steps:
		   1)Open IDE
		   2)File--> Run SQL Script... 
		   3)Select FurnatureDB.sql in Furnature/database folder.
		   4)In prompted window at Default Schema Name, insert: groupproject
			Leave the second option blank.
		   5)Click Run.
		   6)Refresh your schemas, project is imported under the name: groupproject
------------------------------------------------------------------------------------------------------------------------------------------
Frontend setup:	
  General Steps: Open index.html in Furnature/frontendFurnature/index.html with live server extension 
		 under this domain: 127.0.0.1:5500

  Detailed Steps: Visual Studio Code IDE steps:
		  1)In order to run the project you need a live server extension.
			1.a Use Live Server ritwickdey.liveserver.
			1.b If you dont have the live server install it via Extensions menu.
			1.c Open Extensions from the left menu.
				-> Search for ritwickdey.liveserver and install it.

		   2)Open project's files:		
			2.a File--> Open folder...
			2.b Navigate to Furnature project, select frontendFurnature folder and click Select Folder from bottom right
			2.c Now, VS code shows all files of the project
			2.d Find index.html and open it with double click to see it's code
				->Use right click anywhere on the html code and click Open With Live Server
			
			Important: Project MUST open under this domain: 127.0.0.1:5500
------------------------------------------------------------------------------------------------------------------------------------------
Backend setup:  
	1)Insert your MySQL server credentials in application.properties file
		spring.datasource.username=
		spring.datasource.password=
	2)In order to pay with Paypal you can use these credentials:
		Email ID: sb-3owwj5762380@personal.example.com
		System Generated Password: dI,&,2.i
			-> If you want to use your own sandbox account to pay you have to change the:
				paypal.client.id=""
				paypal.client.secret="" 
				in application.properties file

	3)In order to upload photos you should change the app.upload.dir in application.properties
		 under Furnature\groupProjectBackNew\src\main\resources.
		 You should use absolute path pointing to .../Furnature/frontendFurnature/images
			->e.g : app.upload.dir = C:/Users/mcvak/Desktop/!TeamProjectgit/!MAIN/frontEnd/images		
------------------------------------------------------------------------------------------------------------------------------------------

	# Furnature-E-Shop
