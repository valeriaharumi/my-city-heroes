using System.ComponentModel.DataAnnotations.Schema;
using System.ComponentModel.DataAnnotations;

namespace ValeriaHarumi.Api.HeroesApi.Models
{
    [Table("CH_CATEGORY")]
    public class CategoryModel
    {
        [Key]
        [Column("ID")]
        public int Id { get; set; }
        [Column("NAME")]
        public string Name { get; set; }
        [Column("DESCRIPTION")]
        public string Description { get; set; }

        public CategoryModel() { }
    }
}
