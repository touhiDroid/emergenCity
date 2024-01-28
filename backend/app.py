import contextlib

import pymysql
from starlette.applications import Starlette
from starlette.config import Config
from starlette.responses import JSONResponse
from starlette.routing import Route


# Configuration from environment variables or '.env' file.
config = Config('.env')
db_conn = ''
DATABASE_URL = f"mysql://{config('DB_USER')}:{config('DB_PASS')}@{config('DB_HOST')}:{config('DB_PORT')}"


async def say_hello(request):
    name = request.path_params['name']
    return JSONResponse({'message': 'Hello,' + name})


async def get_districts(request):
    # query = district_table.select()
    # results = await database.fetch_all(query)
    curs = db_conn.cursor()
    curs.execute("select * from districts")
    results = curs.fetchall()
    content = [
        {
            "dist_id": result[0],
            "min_lat": result[3],
            "min_lon": result[1],
            "max_lat": result[4],
            "max_lon": result[2],
        }
        for result in results
    ]
    return JSONResponse({'data': content, 'message': 'work in progress ...'})

async def get_fire_occurences(request):
    return JSONResponse({'data': '[]', 'message': 'work in progress ...'})

async def add_fire_occurence(request):
    return JSONResponse({'data': '[]', 'message': 'work in progress ...'})


routes = [
    # Route("/{name}", endpoint=say_hello, methods=['GET']),
    Route("/districts", endpoint=get_districts, methods=['GET']),
    Route("/fires", endpoint=get_fire_occurences, methods=['GET']),
    Route("/fire", endpoint=add_fire_occurence, methods=['POST']),
]

def db_connect():
    global db_conn
    db_conn = pymysql.connect(
        host=config('DB_HOST'),
        user=config('DB_USER'),
        password=config('DB_PASS'),
        db=config('DB_SCHEMA')
    )

db_connect()
app = Starlette(
    routes=routes
)