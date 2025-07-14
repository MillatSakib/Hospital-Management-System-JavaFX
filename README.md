# **Hospital Management System**

## **Overview**

The **Hospital Management System** is a JavaFX-based application designed to streamline and manage hospital operations. It incorporates a robust login and registration system connected to a MySQL database. The application provides role-based features tailored for four types of users:

- **Admin**
- **Doctor**
- **Patient**

## **Features**

### **General**

- Login and registration system with validation

- User-friendly graphical interface built with JavaFX

- MySQL database integration for secure and persistent data storage

### **Admin**

- Doctor Approval: Admin can approve the applied doctor.

- Remove User: If any user are perform any unethical task then admin can remove that user.

### **Doctor**

- View All Patient: Doctor can view all patient who are appointment to him/her. Also can see the prescription which are given by him/her.

- Update patient records and medical history

- Schedule appointments

### **Patient**

- View personal medical history
- Book and manage appointments
- Update personal information

Note: All user like Admin and Doctor will get all features of a patient.

Some Demo Dashboard Picture are given below:

<center> <h2> Login Page </h2> </center>

![userDashboard](/src/View/images/login%20Page.png)

 <center> <h2> Admin Dashboard </h2> </center>

![userDashboard](/src/View/images/adminDashboard.png)

<center> <h2> Doctor Dashboard </h2> </center>

![userDashboard](/src/View/images/doctorDashboard.png)

<center> <h2> Patient Dashboard </h2> </center>

![userDashboard](/src/View/images/userDashboard.png)

## **Technology Stack**

- **Programming Language:** Java (JavaFX framework)
- **Database:** MySQL
- **Development Environment:** NetBeans IDE
- **JavaFX SDK Version:** OpenJFX 23

## **Project Structure**

The project follows a modular structure with packages for controllers, models, and views:

## Folder Structure:

The folder Structure of my project are given below:

```markdown
src/
├── Controller/
│ ├── Admin/
│ ├── Auth/
│ ├── Doctor/
│ ├── Patient/
│ ├── Root/
│ ├── User/
│ └── Main.java
├── Model/
│ ├── Admin.java
│ ├── AllAppointment.java
│ ├── AllPatientForDoctor.java
│ ├── AllPrescription.java
│ ├── AppointmentDoctorList.java
│ ├── ApproveDoctor.java
│ ├── Doctor.java
│ ├── GivePrescription.java
│ ├── MYSQLDatabaseOp.java
│ ├── RemoveUserContainer.java
│ └── User.java
├── View/
│ ├── Admin/
│ ├── Auth/
│ ├── Doctor/
│ ├── Patient/
│ ├── User/
│ └── images/
└── primaryStage.java
```

## Setup & Run:

**Windows:**

- **IDE:** You can use Apache Netbeans 23 IDE for run this project.
- Pre-requisite:

  - You have to installed the JDK in your machine. You can check your jdk version using the command below:

  ```sh
  java --version
  ```

  Here you get the java version then JDK are installed in your machine.

  - You have to added the JavaFX SDK on your NetBeans IDE. For this you will go to the `Tools->Libraries->New Library-> Give a file name -> Add JAR/Folder -> Add all JavaFX sdk jar file (which is inside lib folder. )`. You will get JavaFX SDK on JavaFX framework official site. You also download it from [Here](https://gitlab.com/millatsakib/javafx-sdk/-/raw/main/openjfx-23.0.1_windows-x64_bin-sdk.zip).

  - You will have to add the the MySQL JBDC connector as a library. For this you have go to the `Tools->Libraries->New Library-> Give a file name -> Add JAR/Folder -> Add mysql connector`. You can download the jar file from [Here](https://gitlab.com/millatsakib/mysql-connector-for-java/-/raw/main/mysql-connector-j-8.3.0.jar).

  - You will have to add JavaFX JDK on NetBeans. If you can't add the JavaFX JDK [Watch the video](https://gitlab.com/millatsakib/javafx-jdk/-/raw/main/How_To_Setup_JavaFX_19_and_JDK_19_on_Netbeans_IDE.mp4).You can clone the JavaFX JDK using the command below:
    ```sh
        git clone https://gitlab.com/millatsakib javafx-sdk-download.git
    ```

If your environment are ready like I say then you can run the application. The `main` method are available inside `/src/Controller/Main.java` and from here you have to run the program.

**Linux**

For linux you can follow the same procedure. But as the folder structure are different on Linux. That's why if you face trouble then follow me.

- Af frist you have to install scene builder. You can download scenebuilder from [here](https://1024terabox.com/s/10wvnhlkkJ5w5EUwXvENrhw). After downloading you have to install scence builder. So open terminal on scenebuilder directory and execute the command below:

```sh
sudo dpkg -i SceneBuilder-24.0.1.deb
```

Then you have to confrigure the netbeans for scene Builder. For this go to `Tool->Option`. Then select `Java`. There you get `JavaFX` tab, select that. From There you get Scene Builder path option. Select the path `/opt/scenebuilder` from there.

- Now. You will have to install JavaFX JDK. For that you can download the `JDK FX` from Zulu or azul. Or directly download from here [Here](https://1024terabox.com/s/1gjU2Zv5hqbKqVaKkX9PWlQ). Then you have to install the JDK using the command below.

```sh
sudo dpkg -i zulu24.30.13-ca-fx-jdk24.0.1-linux_amd64.deb
```

After installing it you have to add it on netbeans. For that Go to `Tools->Java Platform`. From there add platform add the JDK from the path `/usr/lib/jvm/zulu-fx-24-amd64`

Now you have to install MySQL driver. For that you have to download the MySQL driver. You can download it from [here](https://1024terabox.com/s/1nwA6Apgp1JKYTUtpMMJYGg). After downloading install it using the command below:

```sql
sudo dpkg -i mysql-connector-j_9.3.0-1ubuntu24.04_all.deb
```

Then you have to connect the driver on NetBeans. For that Go to `Tools->Libraries->Add Library`. Then drop a name `MYSQL` here and click enter. Then click on `Add JAR/folder` and go to `/usr/share/java/` and add all jar file on class path.
