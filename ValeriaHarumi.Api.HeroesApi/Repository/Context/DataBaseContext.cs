using Microsoft.EntityFrameworkCore;
using ValeriaHarumi.Api.HeroesApi.Models;

namespace ValeriaHarumi.Api.HeroesApi.Repository.Context
{
    public class DataBaseContext : DbContext
    {
        public DbSet<ProblemReportModel> ProblemReport { get; set; }

        public DbSet<CategoryModel> Category { get; set; }

        public DbSet<UserModel> User { get; set; }

        public DataBaseContext(DbContextOptions options) : base(options)
        {
        }

        public DataBaseContext()
        {
        }

    }
}
