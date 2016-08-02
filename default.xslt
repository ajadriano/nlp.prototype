<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="text" indent="no"/>
<xsl:strip-space elements="*"/>

<xsl:template match="/S[NP[DT[position() = 1 and @lemma = 'a']][*[@nsubj]] and VP[*[@cop and @lemma='be']]]">
EquivalentClasses(<xsl:apply-templates/>)
</xsl:template>

<xsl:template match="/S[NP[DT[position() = 1 and @lemma = 'all']][*[@nsubj]] and VP[*[@cop and @lemma='be']]]">
SubClassOf(<xsl:apply-templates select="NP"/><xsl:text> </xsl:text><xsl:apply-templates select="VP"/>)
</xsl:template>

<xsl:template match="/S[NNS[@nsubj] and VP[*[@cop and @lemma='be']]]">
SubClassOf(<xsl:apply-templates select="NNS"/><xsl:text> </xsl:text><xsl:apply-templates select="VP"/>)
</xsl:template>

<xsl:template match="/SQ[VBZ[@lemma='be']][NP[position()=1]][NP[position()=2]]">
IsDirectSubClassOf(<xsl:apply-templates select="NP[position()=1]"/><xsl:text> </xsl:text><xsl:apply-templates select="NP[position()=2]"/>)
</xsl:template>

<xsl:template match="NP[DT[position() = 1 and @lemma = 'a']]">
   <xsl:for-each select="*[position()>1]/@lemma">
      <xsl:value-of select="concat(upper-case(substring(.,1,1)), substring(., 2))"/>
   </xsl:for-each>
</xsl:template>

<xsl:template match="NNS">
   <xsl:value-of select="concat(upper-case(substring(@lemma,1,1)), substring(@lemma, 2))"/>
</xsl:template>

<xsl:template match="NP[DT[position() = 1 and @lemma='all']]">
   <xsl:for-each select="*[position()>1]/@lemma">
      <xsl:value-of select="concat(upper-case(substring(.,1,1)), substring(., 2))"/>
   </xsl:for-each>
</xsl:template>

<xsl:template match="VP[VBZ[@cop]]/NP">
ObjectIntersectionOf(<xsl:apply-templates select="NP"/> <xsl:apply-templates select="SBAR"/>)
</xsl:template>

<xsl:template match="VP[VBP[position()=1 and @cop]][NNS]">
   <xsl:value-of select="concat(upper-case(substring(NNS/@lemma,1,1)), substring(NNS/@lemma, 2))"/>
</xsl:template>

<xsl:template match="NP[DT[@lemma = 'any']]">
   <xsl:for-each select="*[position()>1]">
      <xsl:value-of select="concat(upper-case(substring(.,1,1)), substring(., 2))"/>
   </xsl:for-each>
</xsl:template>

<xsl:template match="SBAR[WDT[position() = 1 and @lemma = 'that']][S[count(*)=1]/VP[position() = 1]]">
    <xsl:apply-templates select="S/VP"/>
</xsl:template>

<xsl:template match="VP[VBZ[@relcl]][NP]">
   ObjectSomeValuesFrom(<xsl:value-of select="VBZ/@lemma"/><xsl:text> </xsl:text><xsl:apply-templates select="NP"/>)
</xsl:template>

<xsl:template match="VP[VBZ[@relcl]][NP[count(*)=2][QP[position()=1]][NNS]]">
   ObjectMinCardinality(<xsl:value-of select="NP/QP/CD"/><xsl:text> </xsl:text> <xsl:value-of select="VBZ"/><xsl:value-of select="concat(upper-case(substring(NP/NNS/@lemma,1,1)), substring(NP/NNS/@lemma, 2))"/>)
</xsl:template>
 
<xsl:template match="text()|@*"/>

</xsl:stylesheet>