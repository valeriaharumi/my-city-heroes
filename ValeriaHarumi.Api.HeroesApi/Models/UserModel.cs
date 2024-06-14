using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace ValeriaHarumi.Api.HeroesApi.Models
{
    [Table("CH_USER")]
    public class UserModel
    {
        [Key]
        [Column("ID")]
        public int Id { get; set; }
        [Column("NAME")]
        public string Name { get; set; }
        [Column("EMAIL")]
        public string Email { get; set; }
        [Column("PASSWORD")]
        public string Password { get; set; }
        [Column("USERTYPE")]
        public string UserType { get; set; }

        public UserModel() { }
    }
}
