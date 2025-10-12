package com.arsha.code.userFile.model;

import jakarta.persistence.*;



    @Entity
    @Table(name = "file_details")
    public class FileRecord {
        /*@Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "file_id")
        private Long fileID;*/

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "fileID") // matches DB column
        private Long fileID;

        private String title;
        private String description;

        /*@ManyToOne
        @JoinColumn(name = "user_id")
        private User user;*/

       /* @ManyToOne
        @JoinColumn(name = "user_id", referencedColumnName = "userID") // match foreign key to correct column
        private User user;*/

        @ManyToOne
        @JoinColumn(name = "user_id", referencedColumnName = "userID") // match foreign key
        private User user;


        public Long getFileID() {
            return fileID;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }
    }
