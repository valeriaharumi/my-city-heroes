using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace ValeriaHarumi.Api.HeroesApi.Models
{
    [Table("CH_PROBLEMREPORT")]
    public class ProblemReportModel
    {
        [Key]
        [Column("ID")]
        public int Id { get; set; }
        [Column("DESCRIPTION")]
        public string Description { get; set; } = String.Empty;
        [Column("LATITUDE")]
        public double Latitude { get; set; }
        [Column("LONGITUDE")]
        public double Longitude { get; set; }
        [Column("STATUS")]
        public string Status { get; set; } = "Pending";
        [Column("CITY")]
        public string City { get; set; }
        [Column("CATEGORY")]
        public string Category { get; set; }
        [Column("ID_USER")]
        public int IdUser { get; set; }

        public ProblemReportModel() { }
    }
}
