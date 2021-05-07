package page.page1;

public class CommentData {

        private int s_id;
        private String course_code;
        private String prof_name;
        private String comment;
        private String cid;

        public CommentData() {}

        public CommentData(int s_id, String course_code, String prof_name, String comment) {
            this.cid = String.valueOf(s_id) + course_code;
            this.s_id = s_id;
            this.course_code = course_code;
            this.prof_name = prof_name;
            this.comment = comment;
        }
        public String getCid() { return cid; }
        public void setCid() { this.cid = String.valueOf(s_id)+course_code; }
        public void setS_id(int sid) { this.s_id = sid; }
        public int getSid() {
            return s_id;
        }

        public void setCourse_code(String course_code) {this.course_code = course_code;}
        public String getCourse_code() {
            return course_code;
        }

        public void setProf_name(String prof_name) {this.prof_name = prof_name;}
        public String getProf() {
        return prof_name;
    }

        public void setComment(String content) { this.comment = content; }
        public String getComment() {
        return comment;
    }



}
