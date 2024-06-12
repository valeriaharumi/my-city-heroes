using ValeriaHarumi.Api.HeroesApi.Models;
using ValeriaHarumi.Api.HeroesApi.Repository.Context;

namespace ValeriaHarumi.Api.HeroesApi.Repository
{
    public class ProblemReportRepository
    {
        private readonly DataBaseContext dataBaseContext;
        public ProblemReportRepository(DataBaseContext ctx) 
        {
            dataBaseContext = ctx;
        }

        public List<ProblemReportModel> Listar()
        {
            var lista = new List<ProblemReportModel>();
            lista = dataBaseContext.ProblemReport.ToList<ProblemReportModel>();
            return lista;
        }

        public ProblemReportModel Consultar(int id)
        {
            var problem = dataBaseContext.ProblemReport.Find(id);

            return problem;
        }

        public void Inserir(ProblemReportModel problem)
        {
            dataBaseContext.ProblemReport.Add(problem);
            dataBaseContext.SaveChanges();
        }

        public void Alterar(ProblemReportModel problem)
        {
            dataBaseContext.ProblemReport.Update(problem);
            dataBaseContext.SaveChanges();
        }

        public void Excluir(ProblemReportModel problem)
        {
            dataBaseContext.ProblemReport.Remove(problem);
            dataBaseContext.SaveChanges();
        }
    }
}
