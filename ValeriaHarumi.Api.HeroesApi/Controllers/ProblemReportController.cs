using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Http.Extensions;
using Microsoft.AspNetCore.Mvc;
using ValeriaHarumi.Api.HeroesApi.Models;
using ValeriaHarumi.Api.HeroesApi.Repository;
using ValeriaHarumi.Api.HeroesApi.Repository.Context;

namespace ValeriaHarumi.Api.HeroesApi.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class ProblemReportController : ControllerBase
    {
        private readonly ProblemReportRepository problemReportRepository;

        public ProblemReportController(DataBaseContext context)
        {
            problemReportRepository = new ProblemReportRepository(context);
        }

        [HttpGet]
        public ActionResult<List<ProblemReportModel>> Listar()
        {
            try
            {
                var lista = problemReportRepository.Listar();

                if (lista != null)
                {
                    return Ok(lista);
                }
                else
                {
                    return NotFound();
                }
            }
            catch (Exception ex)
            {
                return StatusCode(500, ex.Message);
            }
        }

        [HttpGet("{id:int}")]
        public ActionResult<ProblemReportModel> Consultar(int id)
        {
            try
            {
                var problemReportModel = problemReportRepository.Consultar(id);

                if (problemReportModel != null)
                {
                    return Ok(problemReportModel);
                }
                else
                {
                    return NotFound();
                }

            }
            catch (Exception e)
            {
                return StatusCode(500, e.Message);
            }
        }

        [HttpPost]
        public ActionResult<ProblemReportModel> Inserir([FromBody] ProblemReportModel problemReportModel)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            try
            {
                problemReportRepository.Inserir(problemReportModel);
                var location = new Uri(Request.GetEncodedUrl() + "/" + problemReportModel.Id);
                return Created(location, problemReportModel);
            }
            catch (Exception error)
            {
                return BadRequest(new { message = $"Não foi possível inserir o problema. Detalhes: {error.Message}" });
            }
        }

        [HttpDelete("{id:int}")]
        public ActionResult<ProblemReportModel> Excluir(int id)
        {
            try
            {
                var problemReportModel = problemReportRepository.Consultar(id);

                if (problemReportModel != null)
                {
                    problemReportRepository.Excluir(problemReportModel);
                    return NoContent();
                }
                else
                {
                    return NotFound();
                }
            }
            catch (Exception e)
            {
                return BadRequest(e.Message);
            }
        }

        [HttpPut("{id:int}")]
        public ActionResult<ProblemReportModel> Alterar([FromRoute] int id, [FromBody] ProblemReportModel problemReportModel)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (problemReportModel.Id != id)
            {
                return NotFound();
            }

            try
            {
                problemReportRepository.Alterar(problemReportModel);
                return NoContent();
            }
            catch (Exception error)
            {
                return BadRequest(new { message = $"Não foi possível alterar o problema. Detalhes: {error.Message}" });
            }
        }
    }
}
