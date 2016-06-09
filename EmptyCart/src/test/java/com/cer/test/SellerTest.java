package com.cer.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.cer.dao.GMDao;
import com.cer.persistent.TruckInformation;
import com.cer.persistent.Seller;
import com.cer.services.SellerService;
import com.cer.util.GeoJsonReader;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.Polygon;
import com.vividsolutions.jts.geom.PrecisionModel;
import com.vividsolutions.jts.io.ParseException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:EmptyCart.xml" })
@TransactionConfiguration(transactionManager = "gmTransactionManager", defaultRollback = false)
@Transactional
public class SellerTest {
	
	protected final Logger logger = LoggerFactory.getLogger(RouteInformationTest.class);
	
	
	@Autowired
	private SellerService sellerService;
	
	/*@Autowired
	private GMDao gmDao;*/
	
	@Test
	public void testSaveWareHouse()
	{
		Seller w = new Seller();
		w.setAddress("A-401, Gaur Global Village, Crossing Republic, Ghaziabad, UP ");
		String servingAreaString = " {        \"type\": \"Polygon\",        \"coordinates\": [          [            [              77.22358703613281,              28.644912597647096            ],            [              77.22320079803467,              28.645100911611944            ],            [              77.22015380859375,              28.644686620443018            ],            [              77.21792221069336,              28.644309990687685            ],            [              77.21620559692383,              28.644197001497385            ],            [              77.21384525299071,              28.64479960910591            ],            [              77.2119140625,              28.6454398758967            ],            [              77.20985412597656,              28.645778839086045            ],            [              77.20951080322266,              28.645628188914912            ],            [              77.20929622650146,              28.643858033196253            ],            [              77.2085666656494,              28.641296903962782            ],            [              77.20805168151855,              28.639865657451313            ],            [              77.20762252807617,              28.63835906108212            ],            [              77.20680713653564,              28.636814777351557            ],            [              77.20547676086426,              28.634893807332773            ],            [              77.20517635345459,              28.63346247347551            ],            [              77.20509052276611,              28.627812280783846            ],            [              77.20616340637207,              28.62649385872282            ],            [              77.20727920532225,              28.626004154882608            ],            [              77.20998287200928,              28.624949400390744            ],            [              77.21294403076172,              28.62283985961478            ],            [              77.21543312072754,              28.6215967175225            ],            [              77.21809387207031,              28.620466575574866            ],            [              77.2190809249878,              28.620353560711077            ],            [              77.2203254699707,              28.620466575574866            ],            [              77.2263765335083,              28.62321656643363            ],            [              77.23045349121094,              28.624761050259174            ],            [              77.23234176635742,              28.625476778961378            ],            [              77.23440170288086,              28.627586266749454            ],            [              77.23594665527344,              28.627925287618567            ],            [              77.23912239074707,              28.626192502783795            ],            [              77.24032402038574,              28.62532609964013            ],            [              77.2413969039917,              28.626644536367888            ],            [              77.24135398864746,              28.628754000688893            ],            [              77.24088191986084,              28.63357547422667            ],            [              77.24075317382812,              28.63557180076677            ],            [              77.24101066589355,              28.639150026873498            ],            [              77.24045276641846,              28.640882597770116            ],            [              77.2379207611084,              28.643104766383157            ],            [              77.23259925842284,              28.642200839067446            ],            [              77.2298526763916,              28.642916448834715            ],            [              77.22796440124512,              28.643556727120107            ],            [              77.22474575042725,              28.645138574364346            ],            [              77.22397327423096,              28.645251562540405            ],            [              77.22358703613281,              28.644912597647096            ]          ]        ]      }    ";
		
		try {
			w.setName("WH1");
			w.setServingAreaJson(servingAreaString);
			w.setLocationJson("{        \"type\": \"Point\",        \"coordinates\": [          77.21972465515137,          28.63568479924653        ]      }");
			sellerService.saveSeller(w);
			
			w = new Seller();
			w.setName("WH2");
			w.setAddress("B-Pancheel, JNU Stadium, New Delhi");
			w.setLocationJson("{        \"type\": \"Point\",        \"coordinates\": [          77.24122524261475,          28.586066772106946        ]      }");
			
			servingAreaString = "{        \"type\": \"Polygon\",        \"coordinates\": [          [            [              77.25251197814941,              28.566054698253403            ],            [              77.25431442260742,              28.56959760257536            ],            [              77.25946426391602,              28.573441895718876            ],            [              77.26633071899414,              28.57826590797759            ],            [              77.26942062377928,              28.580150227678846            ],            [              77.26633071899414,              28.581356174571            ],            [              77.26435661315918,              28.582863588740366            ],            [              77.26118087768553,              28.58452171937042            ],            [              77.2587776184082,              28.588666931556347            ],            [              77.25757598876952,              28.592585890962393            ],            [              77.25568771362305,              28.59846405613708            ],            [              77.25379943847656,              28.6037390526665            ],            [              77.2518253326416,              28.610520801945892            ],            [              77.25088119506836,              28.61300733365442            ],            [              77.24015235900877,              28.612781287749698            ],            [              77.23380088806152,              28.61067150251294            ],            [              77.22925186157227,              28.609993348258573            ],            [              77.22478866577148,              28.604643311197442            ],            [              77.21294403076172,              28.59183225626861            ],            [              77.21148490905762,              28.579773366439284            ],            [              77.20564842224121,              28.56929608344729            ],            [              77.20779418945312,              28.566356226669377            ],            [              77.20839500427246,              28.569069943534203            ],            [              77.2199821472168,              28.5687684228941            ],            [              77.22556114196777,              28.567788474846722            ],            [              77.2313117980957,              28.565602404009493            ],            [              77.24075317382812,              28.564773192846978            ],            [              77.24770545959471,              28.565300873433618            ],            [              77.25251197814941,              28.566054698253403            ]          ]        ]         }";
			w.setServingAreaJson(servingAreaString);
			sellerService.saveSeller(w);
			
			w = new Seller();
			w.setName("WH3");
			w.setLocationJson("{        \"type\": \"Point\",        \"coordinates\": [          77.16865539550781,          28.642690467330326        ]      }");
			w.setServingAreaJson("{        \"type\": \"Polygon\",        \"coordinates\": [          [            [              77.19989776611328,              28.62430900856464            ],            [              77.19474792480469,              28.626117163663878            ],            [              77.20436096191406,              28.64148522441856            ],            [              77.20539093017578,              28.646908708472537            ],            [              77.20573425292969,              28.651428064300305            ],            [              77.20333099365233,              28.6562484958429            ],            [              77.20470428466797,              28.662273723612298            ],            [              77.20985412597656,              28.66438247151326            ],            [              77.20813751220703,              28.671310915880834            ],            [              77.19921112060547,              28.683660484815782            ],            [              77.17723846435547,              28.710463073923208            ],            [              77.15629577636717,              28.698718963995404            ],            [              77.14771270751953,              28.683660484815782            ],            [              77.14050292968749,              28.676130433078256            ],            [              77.13294982910155,              28.66528620762573            ],            [              77.12677001953125,              28.65654976545602            ],            [              77.12608337402344,              28.644498305734405            ],            [              77.1295166015625,              28.62430900856464            ],            [              77.10067749023438,              28.60923983839668            ],            [              77.11784362792969,              28.602005868729954            ],            [              77.13981628417969,              28.60562291582374            ],            [              77.15766906738281,              28.599895867091515            ],            [              77.16934204101562,              28.59567573671796            ],            [              77.18925476074219,              28.60562291582374            ],            [              77.19989776611328,              28.60291014217668            ],            [              77.2067642211914,              28.605924330794295            ],            [              77.2115707397461,              28.609842646718608            ],            [              77.21397399902344,              28.61406220811067            ],            [              77.21054077148436,              28.619788484567405            ],            [              77.19989776611328,              28.62430900856464            ]          ]        ]      }");
			w.setAddress("WH3, Narayna Vihar, New Delhi ");
			sellerService.saveSeller(w);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			
	}
	
	
	@Test
	public void testGetWareHouse()
	{
		List<Seller> result = sellerService.getSellerNearVicinity();
		Assert.assertNotNull("Result is coming null for obtaining All the Warehouses", result );
	}

}
