export class CommonUtilities {

    public static formatDate(date: Date): string {

        if(date == null){
            return "";
          }
      
          let month = date.getMonth() + 1;
          let day = date.getDate();
          let year = date.getFullYear();
          return month + "/" + day + "/" + year;
    }

}