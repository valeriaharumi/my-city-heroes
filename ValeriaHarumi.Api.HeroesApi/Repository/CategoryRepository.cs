using System.Linq;
using ValeriaHarumi.Api.HeroesApi.Models;
using ValeriaHarumi.Api.HeroesApi.Repository.Context;

namespace ValeriaHarumi.Api.HeroesApi.Repository
{
    public class CategoryRepository
    {

        private readonly DataBaseContext dataBaseContext;
        public CategoryRepository(DataBaseContext ctx)
        {
            dataBaseContext = ctx;
        }

        public List<CategoryModel> Listar()
        {
            var lista = new List<CategoryModel>();
            lista = dataBaseContext.Category.ToList<CategoryModel>();
            return lista;
        }

        public CategoryModel Consultar(int id)
        {
            var category = dataBaseContext.Category.Find(id);

            return category;
        }

        public void Inserir(CategoryModel category)
        {
            dataBaseContext.Category.Add(category);
            dataBaseContext.SaveChanges();
        }

        public void Alterar(CategoryModel category)
        {
            dataBaseContext.Category.Update(category);
            dataBaseContext.SaveChanges();
        }

        public void Excluir(CategoryModel category)
        {
            dataBaseContext.Category.Remove(category);
            dataBaseContext.SaveChanges();
        }
    }
}
